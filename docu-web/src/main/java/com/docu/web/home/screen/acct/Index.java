package com.docu.web.home.screen.acct;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.webx.WebxException;
import com.docu.account.dto.AccountBalance;
import com.docu.account.service.AccountBalanceService;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;
import com.docu.web.common.context.EnvUtils;

public class Index {
	@Autowired
	private AccountBalanceService balanceService;
	
	public void execute(TurbineRunData rundata, Context context) throws WebxException {
		HttpSession session = rundata.getRequest().getSession();
		String admin = (String) session.getAttribute("admin");
		String loginUserId = (String) session.getAttribute("loginUserId");
		if (loginUserId == null || loginUserId.length() == 0) {
			rundata.setRedirectLocation(EnvUtils.getContextPath() + "/index.htm");
			return;
		}
		AccountBalance entity = new AccountBalance();
		int pageNum = rundata.getParameters().getInt("pageNum");
		String criteria = rundata.getParameters().getString("userId");
		entity.setUserId(criteria);
		
		QueryBase query = new QueryBase(pageNum, entity);
		PageDO<AccountBalance> page = balanceService.queryAccountBalance(query);
		
		context.put("page", page);
		context.put("admin", admin);
		context.put("userId", criteria);
		context.put("loginUserId", loginUserId);
	}
}
