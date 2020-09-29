package com.model;

public class PasswordResetModel {

	private String newpassword;
	private String confirmpassword;
	private String token;

	public PasswordResetModel() {

	}

	public PasswordResetModel(String newpassword, String confirmpassword, String token) {
		this.newpassword = newpassword;
		this.confirmpassword = confirmpassword;
		this.token = token;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
