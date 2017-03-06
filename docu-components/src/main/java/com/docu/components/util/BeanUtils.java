package com.docu.components.util;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.docu.components.exception.UtilException;

public class BeanUtils {
    private static BeanUtils beanutil;

    public static synchronized BeanUtils getInstance() {
        if (beanutil == null)
            beanutil = new BeanUtils();
        return beanutil;
    }

    private class RelationMethod {
        public Method source, target;
    }

    private Map<String, RelationMethod[]> cache;
    private Map<Class<?>, Method[]> cacheClazzMethods;
    private Map<String, List<String>> cacheClazzFieldNames;//key = class.name + px
    private BeanUtils() {
        cache = new HashMap<String, RelationMethod[]>();
        cacheClazzMethods = new HashMap<Class<?>, Method[]>();
        cacheClazzFieldNames= new HashMap<String, List<String>>();
    }

    public Object cloneBean(Object source, Object target) {
        return copyProperties(source, target, true);
    }

    public Object copyProperties(Object source, Object target) {
        return copyProperties(source, target, false);
    }

    protected Object copyProperties(Object source, Object target, boolean all) {
        Method srcMethod = null, tarMethod = null;
        try {
            RelationMethod[] methods = getMethods(source.getClass(), target.getClass());

            Object[] tmp = new Object[] {};
            for (int i = 0; i < methods.length; i++) {
                srcMethod = methods[i].source;
                Object val = srcMethod.invoke(source, tmp);
                if (!all && val == null) {
                    continue;
                }
                Class type = srcMethod.getReturnType();

                tarMethod = methods[i].target;
                Class[] tarpar = tarMethod.getParameterTypes();
                if (tarpar.length != 1) {
                    String s = "copyProperties()方法只支持一个参数的set方法,而" + target.getClass() + "." + tarMethod.getName() + "()存在的参数个数:"
                            + tarpar.length;
                    throw new RuntimeException(s);
                }
                if (type.isAssignableFrom(tarpar[0])) {
                    Object[] args = new Object[] { val };
                    tarMethod.invoke(target, args);
                }
            }
            return target;
        } catch (IllegalArgumentException e) {
            String err = getMethodKey(source.getClass(), target.getClass()) + "copy property error," + copyPropertiesError(srcMethod, tarMethod);
            throw new UtilException(err);
        } catch (IllegalAccessException e) {
            String err = getMethodKey(source.getClass(), target.getClass()) + "copy property error whem access," + copyPropertiesError(srcMethod, tarMethod);
            throw new UtilException(err);
        } catch (InvocationTargetException e) {
            String err = getMethodKey(source.getClass(), target.getClass()) + "copy property error when reflect," + copyPropertiesError(srcMethod, tarMethod);
            throw new UtilException(err);
        }

    }

    /**
     * 
     * @param bean
     * @param methodName
     * @param value
     */
    public void setPropertis(Object bean, String methodName, Object value) {
        if (bean == null || methodName == null || methodName.length() == 0)
            return;
        Method[] methods = cacheClazzMethods.get(bean.getClass());
        if (methods == null) {
            methods = bean.getClass().getMethods();
            cacheClazzMethods.put(bean.getClass(), methods);
        }

        Method method = null;
        Class[] clz = null;
        String methodName2 = methodName.toLowerCase();

        for (Method m : methods) {
            String name = m.getName();
            if (name.startsWith("set") && methodName2.equals(name.toLowerCase().substring(3))) {
                clz = m.getParameterTypes();
                if (clz.length == 1) {
                    method = m;
                    break;
                }
            }
        }

        if (method == null) {
            throw new UtilException(bean.getClass().getName() + "not existed set" + methodName + ",or the method parameter value not equal to one");
        }

        ValueConvert vc = ValueConvert.getInstance();
        Object v = vc.convert(clz[0], value);
        Object[] args = { v };
        try {
            method.invoke(bean, args);
        } catch (Exception e) {
            String msg = bean.getClass().getName() + "." + (method != null ? method.getName() : methodName);
            throw new UtilException(msg + "=[" + value + "]error in assignment," + e.getMessage());
        }
    }

    /**
     * 
     * @param bean
     * @param methodName
     * @return
     */
    public Object getPropertis(Object bean, String methodName) {
        if (bean == null || methodName == null || methodName.length() == 0)
            throw new UtilException("bean or method name is null, assign value fial!");

        Method[] methods = cacheClazzMethods.get(bean.getClass());
        if (methods == null) {
            methods = bean.getClass().getMethods();
            cacheClazzMethods.put(bean.getClass(), methods);
        }

        Method method = null;
        String methodName2 = methodName.toLowerCase();

        for (Method m : methods) {
            String name = m.getName();
            if (name.startsWith("get") && methodName2.equals(name.toLowerCase().substring(3))) {
                Class[] clz = m.getParameterTypes();
                if (clz.length == 0) {
                    method = m;
                    break;
                }
            }
        }

        if (method == null)
            throw new UtilException(bean.getClass().getName() + "not existed get" + methodName + ",or the method parameter value not equal to zero");

        Object[] args = {};
        try {
            Object rs = method.invoke(bean, args);
            return rs;
        } catch (Exception e) {
            throw new UtilException(bean.getClass().getName() + ".get" + methodName + "get value fail," + e.getMessage());
        }
    }

    private String copyPropertiesError(Method srcMethod, Method tarMethod) {
        String result = "";
        if (srcMethod != null)
            result = "the resource:" + srcMethod.getName();
        if (tarMethod != null) {
            if (result.length() == 0)
                result = ",";
            result += "the target:" + tarMethod.getName();
        }
        return result;
    }

    private String getMethodKey(Class source, Class target) {
        return source.getName() + "-" + target.getName();
    }

    private RelationMethod[] getMethods(Class source, Class target) {
        String key = getMethodKey(source, target);
        RelationMethod[] result = cache.get(key);
        if (result != null)
            return result;

        Method[] srcMethods = source.getMethods();
        Method[] tarMethods = target.getMethods();

        RelationMethod[] methods = new RelationMethod[srcMethods.length];
        int methodsCount = 0;

        for (int i = 0; i < srcMethods.length; i++) {
            if (srcMethods[i] == null)
                continue;

            String src = srcMethods[i].getName();
            if (src.equals("getClass") || !src.substring(0, 3).equals("get")) {
                srcMethods[i] = null;
                continue;
            }

            src = src.substring(3);
            Class type = srcMethods[i].getReturnType();

            for (int j = 0; j < tarMethods.length; j++) {
                if (tarMethods[j] == null)
                    continue;

                String tar = tarMethods[j].getName();
                if (!tar.substring(0, 3).equals("set")) {
                    tarMethods[j] = null;
                    continue;
                }

                tar = tar.substring(3);
                Class[] tarpar = tarMethods[j].getParameterTypes();

                if (tar.equals(src) && tarpar.length == 1 && type.isAssignableFrom(tarpar[0])) {
                    RelationMethod rm = new RelationMethod();
                    rm.source = srcMethods[i];
                    rm.target = tarMethods[j];
                    methods[methodsCount++] = rm;

                    srcMethods[i] = null;
                    tarMethods[j] = null;
                    break;
                }
            }
        }

        result = new RelationMethod[methodsCount];
        for (int i = 0; i < methodsCount; i++) {
            result[i] = methods[i];
        }
        cache.put(key, result);
        return result;
    }

    public Object convert(Class<?> returnType, Object value) {
        return ValueConvert.getInstance().convert(returnType, value);
    }

    public List<String> getFields(Class<?> clazz) {
        return getFields(clazz, null);
    }

    public List<String> getFields(Class<?> clazz, String px) {
        try { 
            String key = clazz.getName() ;
            if(!StringUtils.isEmpty(px)){
                key = key + "-" + px;
            }
            List<String> rs =cacheClazzFieldNames.get(key);
            if(rs!=null){
                return rs;
            }
            Method[] mhs = cacheClazzMethods.get(clazz);
            if (mhs == null) {
                mhs = clazz.getMethods();
                cacheClazzMethods.put(clazz, mhs);
            }

            String px2 = null;
            if (px == null || px.length() != 3) {
                ;
            } else if (px.equalsIgnoreCase("get")) {
                px2 = "get";
            } else if (px.equalsIgnoreCase("set")) {
                px2 = "set";
            }

            rs = new ArrayList<String>();
            for (int i = 0; i < mhs.length; i++) {
                String name = mhs[i].getName();
                if (name.equals("getClass")) {
                    continue;
                } else if ((px2 == null && (name.startsWith("get") || name.startsWith("set"))) || (px2 != null && name.startsWith(px2))) {
                    String n = name.substring(3);
                    if (rs.indexOf(n) < 0) {
                        rs.add(n);
                    }
                }
            }
            cacheClazzFieldNames.put(key,rs);
            return rs;
        } catch (RuntimeException e) {
            throw e;
        }
    }
}

class ValueConvert {
    private static ValueConvert vc;

    public static ValueConvert getInstance() {
        if (vc == null)
            vc = new ValueConvert();
        return vc;
    }

    private Object convertValue(Class<?> clazz, Object vals) {
        if (vals == null) {
            return null;
        }
        Class<?> valclz = vals.getClass();
        if (clazz == valclz || valclz.isAssignableFrom(clazz)) {
            return vals;
        }

        if (clazz.isArray()) {
            clazz = (Class<?>) Array.get(clazz, 0);
        }
        if (vals.getClass().isArray()) {
            vals = Array.get(vals, 0);
        }

        Object result = null;
        if (clazz == vals.getClass()) {
            result = vals;
        } else if (clazz == String.class) {
            result = vals.toString();
        } else if (clazz == int.class || clazz == Integer.class) {
            result = convertNumber(vals, 1);
        } else if (clazz == long.class || clazz == Long.class) {
            result = convertNumber(vals, 2);
        } else if (clazz == float.class || clazz == Float.class) {
            result = convertNumber(vals, 3);
        } else if (clazz == double.class || clazz == Double.class) {
            result = convertNumber(vals, 4);
        } else if (clazz == BigDecimal.class) {
            result = convertNumber(vals, 5);
        } else if (clazz == boolean.class || clazz == Boolean.class) {
            if (vals instanceof Boolean) {
                result = (Boolean) vals;
            } else {
                result = Boolean.valueOf(vals.toString());
            }
        } else if (clazz == java.util.Date.class) {
            if (vals instanceof java.util.Date) {
                result = (java.util.Date) vals;
            } else {
                Calendar c = parseDate(vals.toString());
                if (c != null) {
                    result = c.getTime();
                }
            }
        } else if (clazz == java.sql.Date.class) {
            if (vals instanceof java.sql.Date) {
                result = (java.sql.Date) vals;
            } else if (vals instanceof java.util.Date) {
                java.util.Date d = (java.util.Date) vals;
                result = new java.sql.Date(d.getTime());
            } else {
                Calendar c = parseDate(vals.toString());
                if (c != null) {
                    result = new java.sql.Date(c.getTimeInMillis());
                }
            }
        } else if (clazz == java.sql.Timestamp.class) {
            if (vals instanceof java.sql.Timestamp) {
                result = (java.sql.Timestamp) vals;
            } else if (vals instanceof java.util.Date) {
                java.util.Date d = (java.util.Date) vals;
                result = new java.sql.Timestamp(d.getTime());
            } else {
                Calendar c = parseDate(vals.toString());
                if (c != null) {
                    result = new java.sql.Timestamp(c.getTimeInMillis());
                }
            }
        } else
            throw new UtilException("cannot convert the type:" + clazz.getName());

        return result;
    }
    
    private int parseInt(String s){
    	try{
    		return Integer.parseInt(s);
    	}
    	catch(NumberFormatException e){
    		e.printStackTrace();
    		return -99;
    	}
    }
    
    public Calendar parseDate(String date) {
        if (StringUtils.isEmpty(date)){
            return null;
        }

        boolean format = false;
        int year = 1970, month = 0, day = 1, hrs = 0, min = 0, sec = 0;

        date = date.trim().replace('.', '-').replace('/', '-');
        int i = date.indexOf(' ');
        String time = null;
        if (i > 0) {
            time = date.substring(i + 1);
            date = date.substring(0, i);
        }
        else if(date.indexOf(':')>0){
            time = date;
            date=null;
        }

        if(date!=null){
            String[] d = date.split("-");
            if (format = (d.length == 3)) { 
            		int y= parseInt(d[0]); 
                    if(y!=-99){ 
                    	year=y;
                    }
                    else{
                    	format = false;
                    }
                 
                if (format){
                	int m = parseInt(d[1]);
                	if(m!=-99)
                		month = m;
                	else
                		format = false;
                }
                if (format){
                	int dx = parseInt(d[2]);
                	if(dx!=-99)
                		day = dx;
                	else
                		format = false; 
                }
            }
        }

        if (time != null) {
            String[] t = time.split(":");
            if(t.length == 3){
            	format = true;
            	int x = parseInt(t[0]);
            	if(x!=-99){
            		hrs = x;
            	}
            	else{
            		format = false;
            	} 
            	if (format){
                	int dx = parseInt(t[1]);
                	if(dx!=-99)
                		min = dx;
                	else
                		format = false; 
                }
            	 
                if (format ){
                    String s = t[2];
                    int idx = s.indexOf('-');
                    if(idx>0) {
                        s = s.substring(0,idx);
                    }
                    x = parseInt(s);
                    if(x!=-99){
                        sec = Integer.parseInt(s);
                    }
                    else{
                    	format = false;
                    }
                }
            }
        }

        if (format) {
            Calendar gdate = Calendar.getInstance();
            gdate.set(year, month, day, hrs, min, sec);
            gdate.set(Calendar.MILLISECOND, 0);
            return gdate;
        }
        else{
            return null;
        }
    }
    private Number convertNumber(Object v, int type) {
        if (v == null) {
            return null;
        }
        Number rs = null;
        switch (type) {
        case 1:// int
            if (v instanceof Integer) {
                rs = (Integer) v;
            } else if (v instanceof Number) {
                rs = Integer.valueOf(((Number) v).intValue());
            } else {
                String s = v.toString();
                rs = Integer.valueOf(s);
            }
            break;

        case 2:// long
            if (v instanceof Long) {
                rs = (Long) v;
            } else if (v instanceof Number) {
                rs = Long.valueOf(((Number) v).longValue());
            } else {
                String s = v.toString();
                rs = Long.valueOf(s);
            }
            break;
        case 3:// float
            if (v instanceof Float) {
                rs = (Float) v;
            } else if (v instanceof Number) {
                rs = Float.valueOf(((Number) v).floatValue());
            } else {
                String s = v.toString();
                rs = Float.valueOf(s);
            }
            break;
        case 4:// double
            if (v instanceof Double) {
                rs = (Double) v;
            } else if (v instanceof Number) {
                rs = Double.valueOf(((Number) v).doubleValue());
            } else {
                String s = v.toString();
                rs = Double.valueOf(s);
            }
            break;
        case 5:// BigDecimal
            if (v instanceof BigDecimal) {
                rs = (BigDecimal) v;
            } else if (v instanceof Number) {
                rs = BigDecimal.valueOf(((Number) v).doubleValue());
            } else {
                double d = Double.parseDouble(v.toString());
                rs = BigDecimal.valueOf(d);
            }
        }
        return rs;
    }

    public Object convert(Class clazz, Object value) {
        Object result = null;
        if (clazz.isArray() && value.getClass().isArray()) {
            result = converArrayBaseType(clazz, value);
            if (result == null)
                result = converArray(clazz, value);
        } else
            result = convertValue(clazz, value);
        return result;
    }

    private Object converArray(Class clazz, Object value) {
        Class componentType = clazz.getComponentType();
        Object result = Array.newInstance(componentType, Array.getLength(value));
        for (int i = 0, icount = Array.getLength(value); i < icount; i++) {
            Object o = convertValue(componentType, Array.get(value, i));
            Array.set(result, i, o);
        }
        return result;
    }

    private Object converArrayBaseType(Class clazz, Object value) {
        int size = Array.getLength(value);
        Object result = null;
        if (clazz == int[].class) {
            int[] r = new int[size];
            for (int x = 0; x < size; x++) {
                Object o = Array.get(value, x);
                r[x] = (o == null ? 0 : Integer.parseInt(o.toString()));
            }
            result = r;
        }

        else if (clazz == long[].class) {
            long[] r = new long[size];
            for (int x = 0; x < size; x++) {
                Object o = Array.get(value, x);
                r[x] = (o == null ? 0 : Long.parseLong(o.toString()));
            }
            result = r;
        } else if (clazz == float[].class) {
            float[] r = new float[size];
            for (int x = 0; x < size; x++) {
                Object o = Array.get(value, x);
                r[x] = (o == null ? 0 : Float.parseFloat(o.toString()));
            }
            result = r;
        } else if (clazz == double[].class) {
            double[] r = new double[size];
            for (int x = 0; x < size; x++) {
                Object o = Array.get(value, x);
                r[x] = (o == null ? 0 : Double.parseDouble(o.toString()));
            }
            result = r;
        } else if (clazz == boolean[].class) {
            boolean[] r = new boolean[size];
            for (int x = 0; x < size; x++) {
                Object o = Array.get(value, x);
                r[x] = (o == null ? false : Boolean.parseBoolean(o.toString()));
            }
            result = r;
        }

        return result;
    }
}
