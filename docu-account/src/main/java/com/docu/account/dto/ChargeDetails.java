package com.docu.account.dto;

public class ChargeDetails {
	String chargeId;
	String userId;
	String accountId;
	Float recvAmount;
	String recvTime;
	String percent;
	
	public String getChargeId() {
		return chargeId;
	}
	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
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
	public Float getRecvAmount() {
		return recvAmount;
	}
	public void setRecvAmount(Float recvAmount) {
		this.recvAmount = recvAmount;
	}
	public String getRecvTime() {
		return recvTime;
	}
	public void setRecvTime(String recvTime) {
		this.recvTime = recvTime;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
}
