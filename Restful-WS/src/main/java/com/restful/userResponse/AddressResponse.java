package com.restful.userResponse;

import org.springframework.hateoas.RepresentationModel;

public class AddressResponse extends RepresentationModel<AddressResponse>  {

	private String addressId;
	private String city;
	private String country;
	private String streetName;
	private String postalcode;
	private String type;

	public AddressResponse() {

	}

	public AddressResponse(String addressId, String city, String country, String streetName, String postalcode,
			String type) {
		this.addressId = addressId;
		this.city = city;
		this.country = country;
		this.streetName = streetName;
		this.postalcode = postalcode;
		this.type = type;
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

}
