package com.docu.components.error;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.docu.components.util.StringUtils;

public class AbstractErrorResult implements IErrorResult,Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -8409318374271310614L;
    private boolean success = true;
    private boolean partialError = false;
    private Set<String> errorCodes;
    private Map<String, String> errorMessages;
    private Map<String, Map<String, Object>> messageParamsContext;

    public void addErrorMsg(String code, String errorMsg) {
        if (errorMessages == null) {
            errorMessages = new HashMap<String, String>();
        }
        addError(code);
        errorMessages.put(code, errorMsg);
    }
    public Map<String,Object> getMsgParam(String code){
        if(messageParamsContext == null){
            return null;
        }
        return messageParamsContext.get(code);
    }
    
    public String getErrorMsg() {
        return getErrorMsg('\n');
    }

    public String getErrorMsg(char c) {
        if (errorMessages == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Entry<String, String> entry : errorMessages.entrySet()) {
            sb.append(entry.getValue());
            sb.append(c);
        }
        if (sb.length() > 0) {
            return sb.substring(0, sb.length() - 1);
        }
        return sb.toString();
    }
    
    
    @Override
    public Map<String, Map<String, Object>> getMessageParamsContext() {
        return messageParamsContext;
    }

    @Override
    public Set<String> getErrorCodes() {
        if (null == errorCodes) {
            errorCodes = new HashSet<String>();
        }
        return errorCodes;
    }

    public void addMessageParam(String errorCode, String key, Object value) {
        if (StringUtils.isNotEmpty(errorCode) && StringUtils.isNotEmpty(key) && value != null) {
            if (messageParamsContext == null) {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put(key, value);
                messageParamsContext = new HashMap<String, Map<String, Object>>();
                messageParamsContext.put(errorCode, param);
            } else {
                Map<String, Object> param = messageParamsContext.get(errorCode);
                if (param == null) {
                    param = new HashMap<String, Object>();
                    param.put(key, value);
                    messageParamsContext.put(errorCode, param);
                } else {
                    param.put(key, value);
                }
            }
        }
    }

    public boolean isPartialError() {
        return partialError;
    }

    public void setPartialError(boolean partialError) {
        this.partialError = partialError;
    }

    public void setErrorCodes(Set<String> errorCodes) {
        this.errorCodes = errorCodes;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isFailure() {
        return !success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void addError(String errorCode) {
        if (StringUtils.isEmpty(errorCode)) {
            return;
        }
        getErrorCodes().add(errorCode);
        success = false;
    }

    public List<String> getErrorMessagesList() {
        List<String> list = new ArrayList<String>();
        list.addAll(getErrorMessages().values());
        return list;
    }

    public String getErrorMesssageString() {
        String s = getErrorMessagesList().toString();
        return s.substring(1, s.length() - 1);
    }

    public void addErrors(Collection<String> errors) {
        Iterator<String> ite = errors.iterator();
        while (ite.hasNext()) {
            addError(ite.next());
        }
    }

    public boolean hasError(String errorCode) {
        return getErrorCodes().contains(errorCode);
    }

    public Map<String, String> getErrorMessages() {
        if (errorMessages == null) {
            errorMessages = new HashMap<String, String>();
        }
        return errorMessages;
    }
}
