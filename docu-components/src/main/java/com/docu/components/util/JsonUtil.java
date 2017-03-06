package com.docu.components.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class JsonUtil {

    public static String toJson(Object o){
        return JSON.toJSONString(o);
    }

    public static <T> List<T> parseJsonToList(String jsonStr, Class<T> clazz) {
        return JSON.parseArray(jsonStr, clazz);
    }
    
    public static Map<String, Object> getFiledMap(Object obj, boolean isFilterEmpty, String... filterRegxs) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	Class<?> clazz = obj.getClass();
    	Field[] fields = clazz.getDeclaredFields();
    	try {
			for (Field field : fields) {
				field.setAccessible(true);
				boolean isFilte = false;
				for (String regx : filterRegxs) {
					if(field.getName().equals(regx)){
						isFilte = true;
						break;
					}
				}
				if(isFilte){
					continue;
				}
				Object val = field.get(obj);
				if(isFilterEmpty){
					if(val!=null){
						map.put(field.getName(), val);
					}
				}else{
					map.put(field.getName(), val);
				}				
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
    	return map;
    }
}
