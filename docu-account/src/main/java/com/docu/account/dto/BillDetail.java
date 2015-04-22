package com.docu.account.dto;


public class BillDetail {
	long billId;
	String userId;
	String accountId;
	Float originAmount;
	Float expenseAmount;
	Float balance;
	long activityId;
	String updateBy;
	String updateTime;
	
	public long getBillId() {
		return billId;
	}
	public void setBillId(long billId) {
		this.billId = billId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public Float getOriginAmount() {
		return originAmount;
	}
	public void setOriginAmount(Float originAmount) {
		this.originAmount = originAmount;
	}
	public Float getExpenseAmount() {
		return expenseAmount;
	}
	public void setExpenseAmount(Float expenseAmount) {
		this.expenseAmount = expenseAmount;
	}
	public Float getBalance() {
		return balance;
	}
	public void setBalance(Float balance) {
		this.balance = balance;
	}
	public long getActivityId() {
		return activityId;
	}
	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
