package com.restful.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = "roles")
public class RoleEntity implements Serializable {

	private static final long serialVersionUID = -1510551190485949733L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long rid;

	@Column(nullable = false, length = 20)
	private String name;

	@ManyToMany(mappedBy = "roleEntity")
	private Collection<UserEntity> userEntity;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name="role_authorites",joinColumns = @JoinColumn(name="roles_id", referencedColumnName = "rid")
	,inverseJoinColumns = @JoinColumn(name="auth_id",referencedColumnName = "authid"))
	private Collection<AuthoritiesEntity> authortiesEntity;

	public RoleEntity() {

	}

	public RoleEntity(long rid, String name, Collection<UserEntity> userEntity,
			Collection<AuthoritiesEntity> authortiesEntity) {
		this.rid = rid;
		this.name = name;
		this.userEntity = userEntity;
		this.authortiesEntity = authortiesEntity;
	}

	public long getRid() {
		return rid;
	}

	public void setRid(long rid) {
		this.rid = rid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<UserEntity> getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(Collection<UserEntity> userEntity) {
		this.userEntity = userEntity;
	}

	public Collection<AuthoritiesEntity> getAuthortiesEntity() {
		return authortiesEntity;
	}

	public void setAuthortiesEntity(Collection<AuthoritiesEntity> authortiesEntity) {
		this.authortiesEntity = authortiesEntity;
	}

}
