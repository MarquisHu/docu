package com.docu.components.log;

import org.slf4j.LoggerFactory;

public class Logger {

	private static org.slf4j.Logger svc = LoggerFactory.getLogger("svc");
	private static org.slf4j.Logger exp = LoggerFactory.getLogger("exp");

	public static void svc(String svcKey, String msg, Object... params) {
		if (svc.isWarnEnabled()) {
			StringBuffer sb = new StringBuffer();
			sb.append(svcKey);
			sb.append(",");
			sb.append(msg);
			if (params != null && params.length > 0) {
				for (Object p : params) {
					if (p != null) {
						sb.append(";");
						sb.append(String.valueOf(p));
					}
				}
			}
			svc.warn(sb.toString());
		}
	}

	public static void svc(String msg) {
		if (svc.isWarnEnabled()) {
			svc.warn(msg);
		}
	}

	public static void exp(String svcKey, String msg, Throwable t, Object... params) {
		if (exp.isErrorEnabled()) {
			StringBuffer sb = new StringBuffer();
			sb.append(svcKey);
			sb.append(",");
			sb.append(msg);
			if (params != null && params.length > 0) {
				for (Object p : params) {
					if (p != null) {
						sb.append(";");
						sb.append(String.valueOf(p));
					}
				}
			}
			exp.error(sb.toString(), t);
		}
	}

	public static void stat(String svcKey, String className, String methodName, long time) {
		
	}
}
