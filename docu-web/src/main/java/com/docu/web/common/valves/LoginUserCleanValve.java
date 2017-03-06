package com.docu.web.common.valves;

import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.pipeline.support.AbstractValve;
import com.docu.web.common.context.LoginUserHelper;

public class LoginUserCleanValve extends AbstractValve {

    @Override
    public void invoke(PipelineContext pipelineContext) throws Exception {
        LoginUserHelper.clearUserContext();
    }
}