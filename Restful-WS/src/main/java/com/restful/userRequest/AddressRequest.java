package com.restful.userRequest;

public class AddressRequest {

	private String city;
	private String country;
	private String streetName;
	private String postalcode;
	private String type;

	public AddressRequest() {

	}

	public AddressRequest(String city, String country, String streetName, String postalcode, String type) {

		this.city = city;
		this.country = country;
		this.streetName = streetName;
		this.postalcode = postalcode;
		this.type = type;
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

	
}
