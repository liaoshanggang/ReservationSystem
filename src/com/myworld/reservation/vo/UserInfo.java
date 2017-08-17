package com.myworld.reservation.vo;

public class UserInfo {
	private int userID;
	private String loginName;
	private String loginPass;

	public UserInfo() {
		super();
	}

	public UserInfo(int userID, String loginName, String loginPass) {
		super();
		this.userID = userID;
		this.loginName = loginName;
		this.loginPass = loginPass;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPass() {
		return loginPass;
	}

	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

	@Override
	public String toString() {
		return "UserInfo [userID=" + userID + ", loginName=" + loginName
				+ ", loginPass=" + loginPass + "]";
	}
	
}
