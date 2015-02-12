package com.cimait.invoicec.portal.core.helpers;

import com.cimait.invoicec.core.entity.User;


public class UserInfo extends User {

	private String role ;
	private String newPassword;
	private String newRepeatPassword;

	public String getNewRepeatPassword() {
		return newRepeatPassword;
	}

	public void setNewRepeatPassword(String newRepeatpassword) {
		this.newRepeatPassword = newRepeatpassword;
	}

	public String emails;
	
	
	public String getEmails() {
		return emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
