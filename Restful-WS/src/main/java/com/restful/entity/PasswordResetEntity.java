package com.restful.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "password_token")
@DynamicInsert(true)
@DynamicUpdate(true)
public class PasswordResetEntity implements Serializable {

	private static final long serialVersionUID = 7717506722404361848L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;


	private String token;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "uid")
	private UserEntity userEntity;

	public PasswordResetEntity() {

	}

	public PasswordResetEntity(long id, String token, UserEntity userEntity) {
		this.id = id;
		this.token = token;
		this.userEntity = userEntity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

}
