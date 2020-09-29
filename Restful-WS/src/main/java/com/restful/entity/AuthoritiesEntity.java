package com.restful.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = "authorities")
public class AuthoritiesEntity implements Serializable {

	private static final long serialVersionUID = 6758564685152590808L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long authid;

	@Column(nullable = false, length = 20)
	private String name;

	@ManyToMany(mappedBy = "authortiesEntity")
	private Collection<RoleEntity> roleEntity;

	public AuthoritiesEntity() {

	}

	public AuthoritiesEntity(long authid, String name, Collection<RoleEntity> roleEntity) {
		this.authid = authid;
		this.name = name;
		this.roleEntity = roleEntity;
	}

	public long getAuthid() {
		return authid;
	}

	public void setAuthid(long authid) {
		this.authid = authid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<RoleEntity> getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(Collection<RoleEntity> roleEntity) {
		this.roleEntity = roleEntity;
	}

}
