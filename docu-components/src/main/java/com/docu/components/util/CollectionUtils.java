package com.docu.components.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

public final class CollectionUtils extends org.apache.commons.collections.CollectionUtils {
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static List extractToList(final Collection collection, final String propertyName) {
        List list = new ArrayList(collection.size());
        try {
            for (Object obj : collection) {
                list.add(PropertyUtils.getProperty(obj, propertyName));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return list;
    }

    @SuppressWarnings("rawtypes")
	public static Map createMap(Object... kvs){
    	Map<Object, Object> rs = new HashMap<Object, Object>();
    	for(int i =0 ,size = kvs.length; i < size;i+=2){
    		if(i < size+1){
    			rs.put(kvs[i], kvs[i+1]);
    		}
    	}
    	return rs;
    }
    
    public ArrayList<Object> createList(Object... vals){
        ArrayList<Object> rs = new ArrayList<Object>(vals.length);
        for(Object o : vals){
            rs.add(o);
        }
        return rs;
    } 
}
