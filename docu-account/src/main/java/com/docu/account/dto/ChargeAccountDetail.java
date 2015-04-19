package com.docu.account.dto;

public class ChargeAccountDetail {
	String userId;
	String englishName;
	String accountId;
	String balance;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAccountId() {
		return accountId;
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
}
