package com.docu.components.util.app;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class PropertiesUtil {
	
	private static Properties properties = null;
	
	static {
		InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("properties_app/config.properties");
		properties = new Properties();
			try {
				properties.load(in);
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
	}

	public static String get(String key) {
		String msg = properties.getProperty(key);
		try {
			return  new String(msg.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
}
