package com.docu.account.dto;


public class BillDetail {
	Long billId;
	String userId;
	Long accountId;
	Float originAmount;
	Float expenseAmount;
	Float balance;
	Long activityId;
	String updateBy;
	String updateTime;
	
	public Long getBillId() {
		return billId;
	}
	public void setBillId(Long billId) {
		this.billId = billId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
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
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
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
