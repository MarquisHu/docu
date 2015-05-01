package com.docu.web.home.screen.acct;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.webx.WebxException;
import com.docu.account.dto.AccountDetail;
import com.docu.account.dto.AccountDetailCriteria;
import com.docu.account.service.AccountDetailService;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;
import com.docu.components.constants.app.Constants;
import com.docu.web.common.context.EnvUtils;

public class AccountDetails {
	@Autowired
	private AccountDetailService detailService;
	
	public void execute(TurbineRunData rundata, Context context) throws WebxException {
		HttpSession session = rundata.getRequest().getSession();
		String admin = (String) session.getAttribute("admin");
		String loginUserId = (String) session.getAttribute("loginUserId");
		if (loginUserId == null || loginUserId.length() == 0) {
			rundata.setRedirectLocation(EnvUtils.getContextPath() + "/index.htm");
			return;
		}
		int pageNum = rundata.getParameters().getInt("pageNum");
		String userId = rundata.getParameters().getString("userId");
		String accountId = rundata.getParameters().getString("accountId");
		String payerId = rundata.getParameters().getString("payerId");
		String transactionType = rundata.getParameters().getString("transactionType");
		String activityId = rundata.getParameters().getString("activityId");
		String startDate = rundata.getParameters().getString("startDate");
		String endDate = rundata.getParameters().getString("endDate");
		
		AccountDetailCriteria entity = new AccountDetailCriteria();
		entity.setUserId(userId);
		entity.setAccountId(accountId);
		entity.setPayerId(payerId);
		if (transactionType != null && Constants.TRANSACTION_TYPE_ALL.equals(transactionType)) {
			entity.setTransactionType(null);
		} else {
			entity.setTransactionType(transactionType);
		}
		
		entity.setActivityId(activityId);
		entity.setStartDate(startDate);
		entity.setEndDate(endDate);
		
		QueryBase query = new QueryBase(pageNum, entity);
		PageDO<AccountDetail> page = detailService.queryAccountDetails(query);
		
		context.put("page", page);
		context.put("admin", admin);
		context.put("userId", userId);
		context.put("accountId", accountId);
		context.put("payerId", payerId);
		if (transactionType == null) {
			context.put("transactionType", Constants.TRANSACTION_TYPE_ALL);
		} else {
			context.put("transactionType", transactionType);
		}
		context.put("activityId", activityId);
		context.put("startDate", startDate);
		context.put("endDate", endDate);
		context.put("loginUserId", loginUserId);
	}
}
