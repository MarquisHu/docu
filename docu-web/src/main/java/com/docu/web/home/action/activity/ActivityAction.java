package com.docu.web.home.action.activity;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.docu.account.dto.Account;
import com.docu.account.dto.AccountDetail;
import com.docu.account.service.AccountDetailService;
import com.docu.account.service.AccountService;
import com.docu.activity.dto.Activity;
import com.docu.activity.service.ActivityService;
import com.docu.components.constants.app.Constants;
import com.docu.components.util.DateUtils;
import com.docu.web.common.context.EnvUtils;

public class ActivityAction {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountDetailService detailService;
	
	@Autowired
	private ActivityService activityService;
	
	public void doAddActivity(TurbineRunData rundata, Context context){
		HttpSession session = rundata.getRequest().getSession();
		String loginUserId = (String) session.getAttribute("loginUserId");
		if (loginUserId == null) {
			rundata.setRedirectLocation(EnvUtils.getContextPath() + "/index.htm");
			return;
		}
		String remark = rundata.getParameters().getString("remark");
		String location = rundata.getParameters().getString("location");
		String activityTime = rundata.getParameters().getString("activityTime");
		Float expenseAmount = rundata.getParameters().getFloat("expenseAmount");
		Integer percent = rundata.getParameters().getInt("percent");
		String userIds = rundata.getParameters().getString("userIds");
		String updateTime = DateUtils.formatDate(new Date());
		
		
		String sequenceName = Constants.ACTIVITY_UUID_SEQUENCE_NAME;
		long activityId = activityService.getSequenceUuid(sequenceName);
		
		Activity activity = new Activity();
		activity.setActivityId(activityId);
		activity.setExpenseAmount(expenseAmount);
		activity.setRemark(remark);
		activity.setLocation(location);
		activity.setActivityTime(activityTime);
		activity.setPercent(percent);
		activity.setUpdateBy(loginUserId);
		activity.setUpdateTime(updateTime);
		
		int result = activityService.saveActivity(activity);
		if (result == 1) {
			float commonAmount = (expenseAmount * percent) / 100;
			float privateAmount = expenseAmount - commonAmount;

			String[] userId = userIds.split("\\|");
			int length = userId.length;
			
			privateAmount = privateAmount / length;
			commonAmount = commonAmount + privateAmount % length;
			
			for (int i = 0; i < length; i++) {
				updateAccount(activityId, userId[i], privateAmount, percent, activityTime, loginUserId, updateTime);
			}
			updateAccount(activityId, Constants.COMMON_USER_ID, commonAmount, percent, activityTime, loginUserId, updateTime);
			
			rundata.setRedirectLocation(EnvUtils.getContextPath()+"/activity/index.htm?activityId=" + activityId);
		} else {
			context.put("msg", "充值失败");
		}
	}
	
	private void updateAccount(long activityId, String userId, float changeAmount, int percent, String activityTime, String updateBy, String updateTime) {
		Account account = accountService.queryAccount(userId);
		if (account != null) {
			Float originAmount = account.getBalance();
			Float balance = originAmount - changeAmount;
			
			account.setBalance(balance);
			account.setCommonAmount(balance);
			account.setUpdateBy(updateBy);
			account.setUpdateTime(updateTime);
			
			accountService.updateAccount(account);
			
			String sequenceName = Constants.ACCT_DETAIL_UUID_SEQUENCE_NAME;
			long detailId = accountService.getSequenceUuid(sequenceName);
			
			AccountDetail detail = new AccountDetail();
			detail.setDetailId(detailId);
			detail.setAccountId(account.getAccountId());
			detail.setUserId(account.getUserId());
			detail.setOriginAmount(originAmount);
			detail.setChangeAmount(changeAmount);
			detail.setBalance(balance);
			detail.setPayerId(userId);
			detail.setTransactionType(Constants.TRANSACTION_TYPE_EXPENSE);
			detail.setTransactionTime(activityTime);
			detail.setActivityId(activityId);
			detail.setPercent(percent);
			detail.setUpdateBy(updateBy);
			detail.setUpdateTime(updateTime);
			
			detailService.saveAccountDetail(detail);
		}
	}
}
