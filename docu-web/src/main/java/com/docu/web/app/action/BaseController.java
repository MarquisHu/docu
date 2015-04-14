package com.docu.web.app.action;

import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.docu.components.constants.app.Constants;
import com.docu.components.util.JsonUtil;
import com.docu.web.common.json.app.JsonResult;

@Scope("prototype")
public class BaseController{

	public OutputStream out = null;
	public HttpServletRequest request = null;
	public HttpServletResponse response = null;
	public String json = JsonUtil.toJson(new JsonResult(Constants.RESULT_FAIL,null));

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		try{
			this.request = request;
			this.response = response;
			this.out = response.getOutputStream();
			this.request.setCharacterEncoding("utf-8");
			this.response.setCharacterEncoding("utf-8");  
			this.response.setContentType("application/octet-stream");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String getIpAddr() {      
	      String ip = request.getHeader("x-forwarded-for");      
	      if(StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {      
	          ip = request.getHeader("Proxy-Client-IP");      
	      }      
	      if(StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {      
	          ip = request.getHeader("WL-Proxy-Client-IP");      
	      }      
	      if(StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {      
	           ip = request.getRemoteAddr();      
	      }      
	      return ip;      
	}  
	
	public String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	
}
