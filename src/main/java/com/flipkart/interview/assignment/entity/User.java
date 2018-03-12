package com.flipkart.interview.assignment.entity;

public class User {
	String userName;
	String userType;

	public User(String userName, String userType) {
		super();
		this.userName = userName;
		this.userType = userType;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
