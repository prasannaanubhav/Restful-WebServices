package com.restful.dto;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {

	private static final long serialVersionUID = -5805264250458680398L;
	private long uid;
	private String userId;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String encryptedpassword;
	private String emailVerificationToken;
	private Boolean emailVerificationStatus = false;
	private List<AddressDto> addresses;

	public UserDto() {

	}

	public UserDto(long uid, String userId, String firstname, String lastname, String email, String password,
			String encryptedpassword, String emailVerificationToken, Boolean emailVerificationStatus,
			List<AddressDto> addresses) {
		super();
		this.uid = uid;
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.encryptedpassword = encryptedpassword;
		this.emailVerificationToken = emailVerificationToken;
		this.emailVerificationStatus = emailVerificationStatus;
		this.addresses = addresses;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncryptedpassword() {
		return encryptedpassword;
	}

	public void setEncryptedpassword(String encryptedpassword) {
		this.encryptedpassword = encryptedpassword;
	}

	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}

	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}

	public Boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}

	public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}

	public List<AddressDto> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressDto> addresses) {
		this.addresses = addresses;
	}

}
