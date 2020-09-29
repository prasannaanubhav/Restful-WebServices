package com.restful.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = "userdetails")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 7414466714257503648L;
	@Id
	@GeneratedValue
	private long uid;

	@Column(nullable = false)
	private String userId;

	@Column(nullable = false, length = 10)
	private String firstname;

	@Column(nullable = false, length = 10)
	private String lastname;

	@Column(nullable = false, length = 50)
	private String email;

	@Column(nullable = false, length = 50)
	private String password;

	@Column(nullable = true)
	private String encryptedpassword;

	private String emailVerificationToken;

	@Column(nullable = true)
	private Boolean emailVerificationStatus = false;

	@JsonManagedReference
	@OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<AddressEntity> addresses;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "users_role", joinColumns = @JoinColumn(name = "users_Id", referencedColumnName = "uid"), inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "rid"))
	private Collection<RoleEntity> roleEntity;

	public UserEntity() {

	}

	public UserEntity(long uid, String userId, String firstname, String lastname, String email, String password,
			String encryptedpassword, String emailVerificationToken, Boolean emailVerificationStatus,
			List<AddressEntity> addresses, Collection<RoleEntity> roleEntity) {

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
		this.roleEntity = roleEntity;
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

	public List<AddressEntity> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressEntity> addresses) {
		this.addresses = addresses;
	}

	public Collection<RoleEntity> getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(Collection<RoleEntity> roleEntity) {
		this.roleEntity = roleEntity;
	}

}
