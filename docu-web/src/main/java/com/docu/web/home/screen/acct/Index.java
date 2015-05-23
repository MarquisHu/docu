package com.docu.web.home.screen.acct;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.webx.WebxException;
import com.docu.account.dto.AccountBalanceCriteria;
import com.docu.account.dto.AccountBalanceResult;
import com.docu.account.service.AccountDetailService;
import com.docu.account.service.AccountService;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;
import com.docu.components.constants.app.Constants;
import com.docu.web.common.context.EnvUtils;

public class Index {
	@Autowired
	private AccountService accountService;
	
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
		AccountBalanceCriteria entity = new AccountBalanceCriteria();
		int pageNum = rundata.getParameters().getInt("pageNum");
		String criteria = rundata.getParameters().getString("userId");
		String accountId = rundata.getParameters().getString("accountId");
		String accountType = rundata.getParameters().getString("accountType");
		
		entity.setUserId(criteria);
		entity.setAccountId(accountId);
		if (accountType != null && Constants.ACCOUNT_TYPE_ALL.equals(accountType)) {
			entity.setAccountType(null);
		} else {
			entity.setAccountType(accountType);
		}
		
		QueryBase query = new QueryBase(pageNum, entity);
		PageDO<AccountBalanceResult> page = accountService.queryAccountBalance(query);
		
		entity.setTransactionType(Constants.TRANSACTION_TYPE_CHARGE);
		String incomeAmount = detailService.getTotalBalance(entity);
		
		entity.setTransactionType(Constants.TRANSACTION_TYPE_EXPENSE);
		String expendAmount = detailService.getTotalBalance(entity);
		
		if (incomeAmount == null || "".equals(incomeAmount)) {
			incomeAmount = "0.000";
		}
		if (expendAmount == null || "".equals(expendAmount)) {
			expendAmount = "0.000";
		}
		
		float incomeBalance = Float.parseFloat(incomeAmount);
		float expendBalance = Float.parseFloat(expendAmount);
		float totalBalance = incomeBalance - expendBalance;
		
		DecimalFormat formatter = new DecimalFormat(Constants.FORMATTER_PATTERN);
		BigDecimal totalAmount = new BigDecimal(totalBalance);
	    String balance = formatter.format(totalAmount);
		
		context.put("page", page);
		context.put("balance", balance);
		context.put("incomeAmount", incomeAmount);
		context.put("expendAmount", expendAmount);
		context.put("admin", admin);
		context.put("userId", criteria);
		context.put("accountId", accountId);
		if (accountType == null) {
			context.put("accountType", Constants.ACCOUNT_TYPE_ALL);
		} else {
			context.put("accountType", accountType);
		}
		context.put("loginUserId", loginUserId);
	}
}
