package com.docu.web.common.valves;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.pipeline.support.AbstractValve;
import com.alibaba.citrus.turbine.TurbineRunDataInternal;
import com.alibaba.citrus.turbine.util.TurbineUtil;
import com.docu.components.log.Logger;

public class ExceptionProcessValve extends AbstractValve {
    @Autowired
    private HttpServletRequest request;

    @Override
    public void invoke(PipelineContext pipelineContext) throws Exception {
        TurbineRunDataInternal rundata = (TurbineRunDataInternal) TurbineUtil.getTurbineRunData(request);
        Exception exception = (Exception) pipelineContext.getAttribute("exception");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        pw.close();
        rundata.getContext().put("exception", sw.toString());
        if (exception != null) {
            Logger.exp("PIPELINE-EXP", "there is an exception in action or screent", exception, rundata.getTarget());
            rundata.setTarget("error.vm");
        }
        pipelineContext.invokeNext();
    }
}
