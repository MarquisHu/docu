package com.docu.user.dto;

public class DocumentUser {
	private String userId;
	private String englishName;
	private String chineseName;
	private String telphoneNumber;
	private String active;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public String getChineseName() {
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	public String getTelphoneNumber() {
		return telphoneNumber;
	}
	public void setTelphoneNumber(String telphoneNumber) {
		this.telphoneNumber = telphoneNumber;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
}
