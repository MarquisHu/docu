package com.docu.web.home.screen.acct;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.webx.WebxException;
import com.docu.account.dto.ChargeAccountDetail;
import com.docu.account.service.AccountService;
import com.docu.web.common.context.EnvUtils;

public class ChargeAccount {
	
	@Autowired
	private AccountService accountService;
	
	public void execute(TurbineRunData rundata, Context context) throws WebxException {
		HttpSession session = rundata.getRequest().getSession();
		String systemUserId = (String) session.getAttribute("systemUserId");
		if (systemUserId == null) {
			rundata.setRedirectLocation(EnvUtils.getContextPath() + "/index.htm");
			return;
		}
		String userId = rundata.getParameters().getString("userId");
		if (userId == null) {
			userId = systemUserId;
		}
		ChargeAccountDetail account = accountService.findChargeAccountDetail(userId);
		context.put("account", account);
		context.put("systemUserId", systemUserId);
	}
}
