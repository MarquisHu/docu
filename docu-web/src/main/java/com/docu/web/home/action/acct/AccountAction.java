package com.docu.web.home.action.acct;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.docu.account.model.Account;
import com.docu.account.model.AccountDetail;
import com.docu.account.model.ChargeDetail;
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
	
	public void doSaveCharge(TurbineRunData rundata, Context context) {
		HttpSession session = rundata.getRequest().getSession();
		String admin = (String) session.getAttribute("admin");
		String loginUserId = (String) session.getAttribute("loginUserId");
		if (loginUserId == null) {
			rundata.setRedirectLocation(EnvUtils.getContextPath() + "/index.htm");
			return;
		}
		String userId = rundata.getParameters().getString("userId");
		long accountId = rundata.getParameters().getLong("accountId");
		String payerId = rundata.getParameters().getString("payerId");
		Float recvAmount = rundata.getParameters().getFloat("recvAmount");
		Integer percent = rundata.getParameters().getInt("percent");
		String updateTime = DateUtils.formatDate(new Date());
		int result = 0;
		
		if (Constants.USER_TYPE_ADMIN_NO.equals(admin)) {
			ChargeDetail detail = new ChargeDetail();
			String sequenceName = Constants.CHARGE_UUID_SEQUENCE_NAME;
			long chargeId = accountService.getSequenceUuid(sequenceName);
			
			detail.setChargeId(chargeId);
			detail.setAccountId(accountId);
			detail.setUserId(userId);
			detail.setRecvAmount(recvAmount);
			detail.setRecvTime(updateTime);
			detail.setPayerId(payerId);
			detail.setPercent(percent);
			detail.setStatus(Constants.CHARGE_STATUS_INIT);
			detail.setUpdateBy(loginUserId);
			detail.setUpdateTime(updateTime);
			
			result = detailService.saveChargeDetail(detail);
			if (result == 1) {
				rundata.setRedirectLocation(EnvUtils.getContextPath()+"/acct/chargeDetails.htm?userId=" + userId);
			} else {
				context.put("msg", "充值提交失败");
			}
		} else {
			if (Constants.COMMON_USER_ID.equalsIgnoreCase(userId)) {
				result = updateCommonAccount(userId, payerId, recvAmount, percent, loginUserId, updateTime);
			} else {
				result = updateAccount(loginUserId, userId, payerId, recvAmount, percent, updateTime);
			}
			if (result == 1) {
				rundata.setRedirectLocation(EnvUtils.getContextPath()+"/acct/index.htm?userId=" + userId);
			} else {
				context.put("msg", "充值提交失败");
			}
		}
	}
	
	public void doChargeConfirm(TurbineRunData rundata, Context context) {
		HttpSession session = rundata.getRequest().getSession();
		String loginUserId = (String) session.getAttribute("loginUserId");
		if (loginUserId == null) {
			rundata.setRedirectLocation(EnvUtils.getContextPath() + "/index.htm");
			return;
		}
		String chargeId = rundata.getParameters().getString("chargeId");
		String updateTime = DateUtils.formatDate(new Date());
		
		ChargeDetail detail = detailService.queryChargeDetail(chargeId);
		String userId = detail.getUserId();
		String payerId = detail.getPayerId();
		float recvAmount = detail.getRecvAmount();
		int percent = detail.getPercent();
		
		if (Constants.COMMON_USER_ID.equalsIgnoreCase(userId)) {
			updateCommonAccount(userId, payerId, recvAmount, percent, loginUserId, updateTime);
		} else {
			updateAccount(loginUserId, userId, payerId, recvAmount, percent, updateTime);
		}
		
		detailService.updateChargeDetail(chargeId);
		rundata.setRedirectLocation(EnvUtils.getContextPath()+"/acct/index.htm?userId=" + userId);
	}
	
	public void doChargeUndo(TurbineRunData rundata, Context context) {
		HttpSession session = rundata.getRequest().getSession();
		String loginUserId = (String) session.getAttribute("loginUserId");
		if (loginUserId == null) {
			rundata.setRedirectLocation(EnvUtils.getContextPath() + "/index.htm");
			return;
		}
		String chargeId = rundata.getParameters().getString("chargeId");
		detailService.deleteChargeDetail(chargeId);
	}
	
	private int updateAccount(String loginUserId, String userId, String payerId, float recvAmount, int percent, String updateTime) {
		int result = 0;
		Account account = accountService.queryAccount(userId);
		if (account != null) {
			Float balance = account.getBalance();
			Float commontAmount = (recvAmount * percent) / 100;
			Float privateAmount = recvAmount - commontAmount;
			
			account.setBalance(balance + privateAmount);
			account.setUpdateBy(loginUserId);
			account.setUpdateTime(updateTime);
			
			result = accountService.updateAccount(account);
			if (result == 1) {
				String sequenceName = Constants.ACCT_DETAIL_UUID_SEQUENCE_NAME;
				long detailId = accountService.getSequenceUuid(sequenceName);
				AccountDetail detail = new AccountDetail();
				detail.setDetailId(detailId);
				detail.setAccountId(account.getAccountId());
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
				
				result = detailService.saveAccountDetail(detail);
				updateCommonAccount(userId, payerId, commontAmount, percent, loginUserId, updateTime);
			}
		}
		return result;
	}
	
	private int updateCommonAccount(String userId, String payerId, float changeAmount, int percent, String updateBy, String updateTime) {
		if (changeAmount == 0) {
			return 1;
		}
		int result = 0;
		Account account = accountService.queryAccount(Constants.COMMON_USER_ID);
		if (account != null) {
			Float originAmount = account.getBalance();
			Float balance = originAmount + changeAmount;
			
			account.setBalance(balance);
			account.setUpdateBy(updateBy);
			account.setUpdateTime(updateTime);
			
			result = accountService.updateAccount(account);
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
				
				result = detailService.saveAccountDetail(detail);
			}
		}
		return result;
	}
}
