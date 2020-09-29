package com.restful.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = "addressdetails")
public class AddressEntity implements Serializable {

	private static final long serialVersionUID = 8093652701314937106L;

	@Id
	@GeneratedValue
	private long aid;

	@Column(nullable = false, length = 50)
	private String addressId;

	@Column(nullable = false, length = 30)
	private String city;

	@Column(nullable = false, length = 30)
	private String country;

	@Column(nullable = false, length = 50)
	private String streetName;

	@Column(nullable = false, length = 07)
	private String postalcode;

	@Column(nullable = false, length = 50)
	private String type;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "users_id", referencedColumnName = "uid")
	private UserEntity userEntity;

	public AddressEntity() {

	}

	public AddressEntity(long aid, String addressId, String city, String country, String streetName, String postalcode,
			String type, UserEntity userEntity) {
		this.aid = aid;
		this.addressId = addressId;
		this.city = city;
		this.country = country;
		this.streetName = streetName;
		this.postalcode = postalcode;
		this.type = type;
		this.userEntity = userEntity;
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

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

}
