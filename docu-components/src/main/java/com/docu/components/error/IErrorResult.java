package com.docu.components.error;

import java.util.Map;
import java.util.Set;

public interface IErrorResult {

    /**
     * 
     * @return
     */
    public Set<String> getErrorCodes();

    /**
     * 
     * @return
     */
    public Map<String, Map<String, Object>> getMessageParamsContext();
}
