package com.docu.account.dto;


public class ChargeDetail {
	Long chargeId;
	String userId;
	Long accountId;
	Float recvAmount;
	String recvTime;
	Integer percent;
	String updateBy;
	String updateTime;
	
	public Long getChargeId() {
		return chargeId;
	}
	public void setChargeId(Long chargeId) {
		this.chargeId = chargeId;
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
