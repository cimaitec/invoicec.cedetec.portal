package com.cimait.invoicec.portal.core.helpers;

public class LoginCredentials {
	private String userName;
	private String password;
	private String token;
	private String userDescription;
	private String userType;
	private String userRole;
	
	public LoginCredentials() {
	}
	
	public LoginCredentials(String userName, String password, String token,
			String userDescription, String userType, String userRole) {
		super();
		this.userName = userName;
		this.password = password;
		this.token = token;
		this.userDescription = userDescription;
		this.userType = userType;
		this.userRole = userRole;
	}
	
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUserDescription() {
		return userDescription;
	}
	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}
	
	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	
}
