package com.docu.web.home.screen;

import javax.servlet.http.HttpSession;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.webx.WebxException;

public class Index {

	public void execute(TurbineRunData rundata, Context context) throws WebxException {
		HttpSession session = rundata.getRequest().getSession();
		Long userId = (Long) session.getAttribute("userId");
		if(userId != null && userId > 0){
			rundata.setRedirectTarget("/admin/index.vm");
		}
	}
}
