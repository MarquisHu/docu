package com.docu.components.exception;

public class DaoException extends DefaultException {

    /**
     * 
     */
    private static final long serialVersionUID = -3019120232540916022L;

    public DaoException(String msg){
        super(msg);
    }
    
    public DaoException(String msg, Throwable t){
        super(msg, t);
    }
}
