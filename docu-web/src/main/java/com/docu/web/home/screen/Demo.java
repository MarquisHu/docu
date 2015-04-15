package com.docu.web.home.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.webx.WebxException;

public class Demo {

	public void execute(TurbineRunData rundata, Context context) throws WebxException {
		rundata.setRedirectTarget("/demo.vm");
	}
}
