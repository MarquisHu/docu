package com.docu.account.dto;

public class ChargeAccountDetail {
	String userId;
	String userName;
	Long accountId;
	String balance;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getAccountId() {
		return accountId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
}
