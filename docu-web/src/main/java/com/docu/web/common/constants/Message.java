package com.docu.web.common.constants;
public class Message {
    
    private String status;
    private String msg;
    
    public Message(String status) {
        this.status=status;
    }
    public Message(String status,String msg) {
        this.status=status;
        this.msg = msg;
    }
    public String getStatus() {
        return status;
    }
    public String getMsg() {
        return msg;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
