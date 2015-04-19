package com.docu.account.dto;

import java.util.Date;

public class Account {
	String accountId;
	String userId;
	Float balanceAmount;
	Float privateAmount;
	Float commonAmount;
	String updateBy;
	Date updateTime;
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Float getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(Float balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	public Float getPrivateAmount() {
		return privateAmount;
	}
	public void setPrivateAmount(Float privateAmount) {
		this.privateAmount = privateAmount;
	}
	public Float getCommonAmount() {
		return commonAmount;
	}
	public void setCommonAmount(Float commonAmount) {
		this.commonAmount = commonAmount;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
