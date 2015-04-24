package com.docu.web.home.action.activity;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.docu.account.dto.Account;
import com.docu.account.dto.BillDetail;
import com.docu.account.service.AccountService;
import com.docu.account.service.BillService;
import com.docu.activity.dto.Activity;
import com.docu.activity.service.ActivityService;
import com.docu.components.constants.app.Constants;
import com.docu.components.util.DateUtils;
import com.docu.web.common.context.EnvUtils;

public class ActivityAction {
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ActivityService activityService;
	
	public void doAddActivity(TurbineRunData rundata, Context context){
		HttpSession session = rundata.getRequest().getSession();
		String loginUserId = (String) session.getAttribute("loginUserId");
		if (loginUserId == null) {
			rundata.setRedirectLocation(EnvUtils.getContextPath() + "/index.htm");
			return;
		}
		String userIds = rundata.getParameters().getString("userIds");
		Float expenseAmount = rundata.getParameters().getFloat("expenseAmount");
		String remark = rundata.getParameters().getString("remark");
		String location = rundata.getParameters().getString("location");
		String activityTime = rundata.getParameters().getString("activityTime");
		Integer percent = rundata.getParameters().getInt("percent");
		String updateTime = DateUtils.formatDate(new Date());
		
		
		String sequenceName = Constants.ACTIVITY_UUID_SEQUENCE_NAME;
		long activityId = activityService.getSequenceUuid(sequenceName);
		
		Activity activity = new Activity();
		activity.setActivityId(activityId);
		activity.setExpenseAmount(expenseAmount);
		activity.setRemark(remark);
		activity.setLocation(location);
		activity.setActivityTime(activityTime);
		activity.setUpdateBy(loginUserId);
		activity.setUpdateTime(updateTime);
		
		int result = activityService.saveActivity(activity);
		if (result == 1) {
			insertBillDetail(loginUserId, activityId, userIds, expenseAmount, percent, updateTime);
			rundata.setRedirectLocation(EnvUtils.getContextPath()+"/activity/index.htm?activityId=" + activityId);
		} else {
			context.put("msg", "充值失败");
		}
	}
	
	private void insertBillDetail(String loginUserId, long activityId, String userIds, Float amount, int percent, String updateTime) {
		String[] userId = userIds.split("\\|");
		int length = userId.length;
		if (length == 0) {
			return;
		}
		
		Float expenseAmount = amount / length;
		
		for (int i = 0; i < length; i++) {
			Account account = accountService.queryAccount(userId[i]);
			Float balanceAmount = account.getBalanceAmount();
			
			Float balance = balanceAmount - expenseAmount;
			Float privateAmount = (expenseAmount * percent) / 100;
			Float commontAmount = expenseAmount - privateAmount;
			
			String sequenceName = Constants.BILL_UUID_SEQUENCE_NAME;
			long billId = accountService.getSequenceUuid(sequenceName);
			
			BillDetail bill = new BillDetail();
			bill.setBillId(billId);
			bill.setUserId(userId[i]);
			bill.setAccountId(account.getAccountId());
			bill.setOriginAmount(balanceAmount);
			bill.setExpenseAmount(expenseAmount);
			bill.setBalance(balance);
			bill.setActivityId(activityId);
			bill.setUpdateBy(loginUserId);
			bill.setUpdateTime(updateTime);
			billService.saveBillDetail(bill);
			
			account.setBalanceAmount(balance);
			account.setPrivateAmount(account.getPrivateAmount() - privateAmount);
			account.setCommonAmount(account.getCommonAmount() - commontAmount);
			account.setUpdateBy(loginUserId);
			account.setUpdateTime(updateTime);
			accountService.updateAccount(account);
		}
	}
}
