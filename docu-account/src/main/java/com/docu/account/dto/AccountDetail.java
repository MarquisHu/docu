package com.docu.account.dto;


public class AccountDetail {
	Long detailId;
	Long accountId;
	String userId;
	Float originAmount;
	Float changeAmount;
	Float balance;
	String payerId;
	String transactionType;
	String transactionTime;
	Long activityId;
	Integer percent;
	String updateBy;
	String updateTime;
	
	public Long getDetailId() {
		return detailId;
	}
	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Float getOriginAmount() {
		return originAmount;
	}
	public void setOriginAmount(Float originAmount) {
		this.originAmount = originAmount;
	}
	public Float getChangeAmount() {
		return changeAmount;
	}
	public void setChangeAmount(Float changeAmount) {
		this.changeAmount = changeAmount;
	}
	public Float getBalance() {
		return balance;
	}
	public void setBalance(Float balance) {
		this.balance = balance;
	}
	public String getPayerId() {
		return payerId;
	}
	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}
	public Long getActivityId() {
		return activityId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public Integer getPercent() {
		return percent;
	}
	public void setPercent(Integer percent) {
		this.percent = percent;
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
