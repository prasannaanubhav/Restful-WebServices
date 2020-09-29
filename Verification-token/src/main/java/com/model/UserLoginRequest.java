package com.model;

import java.io.Serializable;

public class UserLoginRequest implements Serializable {

	private static final long serialVersionUID = -674609513532600307L;
	
	private String email;
	private String password;

	public UserLoginRequest() {
		
	}
	
	public UserLoginRequest(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
