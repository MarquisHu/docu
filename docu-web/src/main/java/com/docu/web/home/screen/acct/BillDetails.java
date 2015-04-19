package com.docu.web.home.screen.acct;

import javax.servlet.http.HttpSession;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.webx.WebxException;

public class BillDetails {
	
	public void execute(TurbineRunData rundata, Context context) throws WebxException {
		HttpSession session = rundata.getRequest().getSession();
		String systemUserId = (String) session.getAttribute("systemUserId");
		context.put("systemUserId", systemUserId);
	}
}
