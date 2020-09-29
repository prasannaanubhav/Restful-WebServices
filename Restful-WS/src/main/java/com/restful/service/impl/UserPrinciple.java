package com.restful.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import com.restful.entity.AuthoritiesEntity;
import com.restful.entity.RoleEntity;
import com.restful.entity.UserEntity;

@Transactional
public class UserPrinciple implements UserDetails {

	private UserEntity userEntity;
	
	public UserPrinciple(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	private static final long serialVersionUID = -6528074913257047560L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		List<AuthoritiesEntity> authGranted = new ArrayList<>();
		
		Collection<RoleEntity> roles  = userEntity.getRoleEntity();
		if(roles==null) {
			return grantedAuthorities;
		}
	
		for (RoleEntity roleEntity : roles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(roleEntity.getName()));
			authGranted.addAll(roleEntity.getAuthortiesEntity());
		}
		
		for (AuthoritiesEntity authEntity : authGranted) {
			grantedAuthorities.add(new SimpleGrantedAuthority(authEntity.getName()));
		}
		
		
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		
		return this.userEntity.getEncryptedpassword();
	}

	@Override
	public String getUsername() {
		
		return this.userEntity.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.userEntity.getEmailVerificationStatus();
	}

}
