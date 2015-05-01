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

public class AccountAction {
	
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
		String payerId = rundata.getParameters().getString("payerId");
		long accountId = rundata.getParameters().getLong("accountId");
		Float recvAmount = rundata.getParameters().getFloat("recvAmount");
		Integer percent = rundata.getParameters().getInt("percent");
		String updateTime = DateUtils.formatDate(new Date());
		
		if (Constants.COMMON_USER_ID.equalsIgnoreCase(userId)) {
			updateCommonAccount(userId, payerId, recvAmount, percent, loginUserId, updateTime);
			rundata.setRedirectLocation(EnvUtils.getContextPath()+"/acct/index.htm?userId=" + userId);
			return;
		}
		
		Account account = accountService.queryAccount(userId);
		if (account != null) {
			Float balance = account.getBalance();
			Float commontAmount = (recvAmount * percent) / 100;
			Float privateAmount = recvAmount - commontAmount;
			
			account.setAccountId(accountId);
			account.setBalance(balance + privateAmount);
			if (account.getCommonAmount() == null) {
				account.setCommonAmount(commontAmount);
			} else {
				account.setCommonAmount(account.getCommonAmount() + commontAmount);
			}
			account.setUpdateBy(loginUserId);
			account.setUpdateTime(updateTime);
			
			int result = accountService.updateAccount(account);
			if (result == 1) {
				String sequenceName = Constants.ACCT_DETAIL_UUID_SEQUENCE_NAME;
				long detailId = accountService.getSequenceUuid(sequenceName);
				AccountDetail detail = new AccountDetail();
				detail.setDetailId(detailId);
				detail.setAccountId(accountId);
				detail.setUserId(account.getUserId());
				detail.setOriginAmount(balance);
				detail.setChangeAmount(privateAmount);
				detail.setBalance(balance + privateAmount);
				detail.setPayerId(payerId);
				detail.setTransactionType(Constants.TRANSACTION_TYPE_CHARGE);
				detail.setTransactionTime(updateTime);
				detail.setActivityId(null);
				detail.setPercent(percent);
				detail.setUpdateBy(loginUserId);
				detail.setUpdateTime(updateTime);
				
				detailService.saveAccountDetail(detail);
				updateCommonAccount(userId, payerId, commontAmount, percent, loginUserId, updateTime);
				rundata.setRedirectLocation(EnvUtils.getContextPath()+"/acct/index.htm?userId=" + userId);
			} else {
				context.put("msg", "充值失败");
			}
		} else {
			context.put("msg", "充值失败");
		}
	}
	
	private void updateCommonAccount(String userId, String payerId, float changeAmount, int percent, String updateBy, String updateTime) {
		Account account = accountService.queryAccount(Constants.COMMON_USER_ID);
		if (account != null) {
			Float originAmount = account.getBalance();
			Float balance = originAmount + changeAmount;
			
			account.setBalance(balance);
			account.setCommonAmount(balance);
			account.setUpdateBy(updateBy);
			account.setUpdateTime(updateTime);
			
			int result = accountService.updateAccount(account);
			if (result == 1) {
				String sequenceName = Constants.ACCT_DETAIL_UUID_SEQUENCE_NAME;
				long detailId = accountService.getSequenceUuid(sequenceName);
				AccountDetail detail = new AccountDetail();
				detail.setDetailId(detailId);
				detail.setAccountId(account.getAccountId());
				detail.setUserId(account.getUserId());
				detail.setOriginAmount(originAmount);
				detail.setChangeAmount(changeAmount);
				detail.setBalance(balance);
				detail.setPayerId(payerId);
				detail.setTransactionType(Constants.TRANSACTION_TYPE_CHARGE);
				detail.setTransactionTime(updateTime);
				detail.setActivityId(null);
				detail.setPercent(percent);
				detail.setUpdateBy(updateBy);
				detail.setUpdateTime(updateTime);
				
				detailService.saveAccountDetail(detail);
			}
		}
	}
}
