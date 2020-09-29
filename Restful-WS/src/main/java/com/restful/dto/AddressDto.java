package com.restful.dto;

import java.io.Serializable;

public class AddressDto implements Serializable {

	private static final long serialVersionUID = 7390639210252957093L;

	private long aid;
	private String addressId;
	private String city;
	private String country;
	private String streetName;
	private String postalcode;
	private String type;
	private UserDto userDto;

	public AddressDto() {

	}

	public AddressDto(long aid, String addressId, String city, String country, String streetName, String postalcode,
			String type, UserDto userDto) {

		this.aid = aid;
		this.addressId = addressId;
		this.city = city;
		this.country = country;
		this.streetName = streetName;
		this.postalcode = postalcode;
		this.type = type;
		this.userDto = userDto;
	}

	public long getAid() {
		return aid;
	}

	public void setAid(long aid) {
		this.aid = aid;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

}
