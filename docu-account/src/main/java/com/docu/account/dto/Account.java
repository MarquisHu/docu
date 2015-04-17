package com.docu.account.dto;

public class Account {
	String accountId;
	String userId;
	String balanceAmount;
	String privateAmount;
	String commonAmount;
	String updateBy;
	String updateTime;
	
	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the balanceAmount
	 */
	public String getBalanceAmount() {
		return balanceAmount;
	}
	/**
	 * @param balanceAmount the balanceAmount to set
	 */
	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	/**
	 * @return the privateAmount
	 */
	public String getPrivateAmount() {
		return privateAmount;
	}
	/**
	 * @param privateAmount the privateAmount to set
	 */
	public void setPrivateAmount(String privateAmount) {
		this.privateAmount = privateAmount;
	}
	/**
	 * @return the commonAmount
	 */
	public String getCommonAmount() {
		return commonAmount;
	}
	/**
	 * @param commonAmount the commonAmount to set
	 */
	public void setCommonAmount(String commonAmount) {
		this.commonAmount = commonAmount;
	}
	/**
	 * @return the updateBy
	 */
	public String getUpdateBy() {
		return updateBy;
	}
	/**
	 * @param updateBy the updateBy to set
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
