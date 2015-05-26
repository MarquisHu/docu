package com.docu.web.home.screen;

import javax.servlet.http.HttpSession;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.webx.WebxException;
import com.docu.web.common.context.EnvUtils;

public class Index {

	public void execute(TurbineRunData rundata, Context context) throws WebxException {
		HttpSession session = rundata.getRequest().getSession();
		String loginUserId = (String) session.getAttribute("loginUserId");
		if (loginUserId != null && loginUserId.length() != 0) {
			context.put("loginUserId", loginUserId);
			rundata.setRedirectLocation(EnvUtils.getContextPath() + "/acct/index.htm?userId=" + loginUserId);
			return;
		}
	}
}
