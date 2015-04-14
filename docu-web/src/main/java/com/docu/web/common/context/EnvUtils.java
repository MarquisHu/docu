package com.docu.web.common.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.alibaba.citrus.turbine.TurbineRunData;
import com.docu.components.exception.DefaultException;
import com.docu.components.util.StringUtils;


public class EnvUtils implements ServletContextListener{
	private static String contextPath;
	private static int HTTP_LEN="http://".length();
	
	public static String addContextPath(String path,TurbineRunData rundata){ 
		if(contextPath == null){
			if( rundata==null){
				throw new DefaultException("Context path uninitialized!");
			}
			contextPath = rundata.getRequest().getContextPath();
		}
		if(StringUtils.isEmpty(path) || path.length()<= HTTP_LEN ||
				contextPath.length()==0 || (contextPath.length()==1 && contextPath.charAt(1)=='/')){
			return path;
		}
		
		int start = path.indexOf('/',HTTP_LEN+1);
		String rs;
		if(start>0){
			rs = path.substring(0,start) + contextPath + path.substring(start); 
		}
		else{ 
			rs = (path.charAt(path.length()-1)=='/' ? path.substring(0,path.length()-1) :path) + contextPath;
		} 
		return rs;
	}
	/** add context*/
	public static String addContextPath(String path){ 
		return addContextPath(path,null);
	}
	public static String getContextPath() {
		return contextPath;
	}
	public static void setContextPath(String p) {
		contextPath = p;
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}
	@Override
	public void contextInitialized(ServletContextEvent context) { 
		setContextPath(context.getServletContext().getContextPath());
	}
}
