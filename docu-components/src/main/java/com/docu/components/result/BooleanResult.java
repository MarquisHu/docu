package com.docu.components.result;

import java.io.Serializable;

import com.docu.components.error.AbstractErrorResult;

public class BooleanResult extends AbstractErrorResult implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1204259208755389777L;

    public BooleanResult() {
        super();
    }

    public BooleanResult(String errorCode, String errorMessage) {
        super();
        addErrorMsg(errorCode, errorMessage);
    }
}
