package com.docu.components.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils extends org.apache.commons.beanutils.PropertyUtils {
	private Properties propertie;
	private FileInputStream inputFile;

	public void initProperty(String filePath) {
		propertie = new Properties();
		try {
			inputFile = new FileInputStream(filePath);
			propertie.load(inputFile);
			inputFile.close();
		} catch (FileNotFoundException ex) {
			System.out.println("read the file properties fail!reason:file path wrong or file not existed");
			ex.printStackTrace();
		} catch (IOException ex) {
			System.out.println("load the file - fail!");
			ex.printStackTrace();
		}

	}

	public String getPropertyValue(String key) {
		if (propertie.containsKey(key)) {
			String value = propertie.getProperty(key);
			return value;
		} else {
			return null;
		}
	}
}
