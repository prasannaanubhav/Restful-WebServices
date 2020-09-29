package com.restful.userRequest;

import java.util.List;

public class UserRequest {

	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private List<AddressRequest> addresses;

	public UserRequest() {

	}

	public UserRequest(String firstname, String lastname, String email, String password,
			List<AddressRequest> addresses) {

		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.addresses = addresses;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public List<AddressRequest> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressRequest> addresses) {
		this.addresses = addresses;
	}
	
	

}