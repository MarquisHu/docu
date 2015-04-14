package com.docu.web.common.json.app;

public class JsonResult {

	private int code = 0;
    private String msg = "";
    private Object data = new Object();
    
    public JsonResult(){}
    
    public JsonResult(String result,Object data){
    	this.code = Integer.valueOf(result.split(":")[0]);
    	this.msg  = result.split(":")[1];
    	this.data = data;
    }
    
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	public Object getData() {
		return data;
	}
}
