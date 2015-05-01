package com.docu.web.home.action.acct;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.docu.account.dto.Account;
import com.docu.account.dto.AccountDetail;
import com.docu.account.service.AccountDetailService;
import com.docu.account.service.AccountService;
import com.docu.components.constants.app.Constants;
import com.docu.components.util.DateUtils;
import com.docu.web.common.context.EnvUtils;

public class AccountDetailAction {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountDetailService detailService;
	
	public void doSaveCharge(TurbineRunData rundata, Context context){
		HttpSession session = rundata.getRequest().getSession();
		String loginUserId = (String) session.getAttribute("loginUserId");
		if (loginUserId == null) {
			rundata.setRedirectLocation(EnvUtils.getContextPath() + "/index.htm");
			return;
		}
		String userId = rundata.getParameters().getString("userId");
		long accountId = rundata.getParameters().getLong("accountId");
		Float recvAmount = rundata.getParameters().getFloat("recvAmount");
		Integer percent = rundata.getParameters().getInt("percent");
		
		Account account = accountService.queryAccount(userId);
		if (account != null) {
			Float privateAmount = (recvAmount * percent) / 100;
			Float commontAmount = recvAmount - privateAmount;
			Float balance = account.getBalance();
			String updateTime = DateUtils.formatDate(new Date());
			
			account.setUserId(userId);
			account.setAccountId(accountId);
			account.setBalance(balance + recvAmount);
			account.setCommonAmount(account.getCommonAmount() + commontAmount);
			account.setUpdateBy(loginUserId);
			account.setUpdateTime(updateTime);
			
			int result = accountService.updateAccount(account);
			if (result == 1) {
				String sequenceName = Constants.ACCT_DETAIL_UUID_SEQUENCE_NAME;
				long detailId = accountService.getSequenceUuid(sequenceName);
				AccountDetail detail = new AccountDetail();
				detail.setDetailId(detailId);
				detail.setAccountId(accountId);
				detail.setUserId(userId);
				detail.setOriginAmount(balance);
				detail.setChangeAmount(recvAmount);
				detail.setBalance(balance + recvAmount);
				detail.setActivityId(null);
				detail.setTransactionType(Constants.TRANSACTION_TYPE_CHARGE);
				detail.setTransactionTime(updateTime);
				detail.setUpdateBy(loginUserId);
				detail.setUpdateTime(updateTime);
				
				result = detailService.saveAccountDetail(detail);
				if (result == 1) {
					rundata.setRedirectLocation(EnvUtils.getContextPath()+"/acct/index.htm?userId=" + userId);
				} else {
					context.put("msg", "充值失败");
				}
			} else {
				context.put("msg", "充值失败");
			}
		} else {
			context.put("msg", "充值失败");
		}
	}
}
