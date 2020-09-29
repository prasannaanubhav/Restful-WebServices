package com.restful.userResponse;

import java.util.List;

import com.restful.userRequest.AddressRequest;

public class UserResponse {

	private String userId;
	private String firstname;
	private String lastname;
	private String email;
	private List<AddressResponse> addresses;

	public UserResponse() {

	}

	public UserResponse(String userId, String firstname, String lastname, String email,
			List<AddressResponse> addresses) {
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.addresses = addresses;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public List<AddressResponse> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressResponse> addresses) {
		this.addresses = addresses;
	}

}
