package com.restful;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.restful.entity.AddressEntity;
import com.restful.entity.AuthoritiesEntity;
import com.restful.entity.RoleEntity;
import com.restful.entity.UserEntity;
import com.restful.repository.AuthorityRepository;
import com.restful.repository.Rolerepository;
import com.restful.repository.UserRepository;
import com.restful.security.Utility;

@Component
public class InitialUserSetup {

	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private Rolerepository roleRepository;

	@Autowired
	private Utility utility;

	@Autowired
	private BCryptPasswordEncoder bCrypt;

	@Autowired
	private UserRepository userRepository;
	
	UserEntity userEntity;

	@EventListener
	@Transactional
	public void onApplicationEvent(ApplicationReadyEvent ready) {

		AuthoritiesEntity readValue = createReadWriteDeleteOperation("READ_OPERATION");
		AuthoritiesEntity writeValue = createReadWriteDeleteOperation("WRITE_OPERATION");
		AuthoritiesEntity deleteValue = createReadWriteDeleteOperation("DELETE_OPERATION");
		RoleEntity userRole = createRole("ROLE_USER", Arrays.asList(readValue, writeValue));
		RoleEntity adminRole = createRole("ROLE_ADMIN", Arrays.asList(readValue, writeValue, deleteValue));
		if (adminRole == null)
			return;
		userEntity = new UserEntity();
		userEntity.setFirstname("Maverick");
		userEntity.setLastname("Thompson");
		userEntity.setEmail("Maverick@gmail.com");
		userEntity.setPassword("Mav123");
		userEntity.setEmailVerificationStatus(true);
		userEntity.setEmailVerificationToken(utility.generateEmailToken(utility.generateUserId(30)));
		userEntity.setUserId(utility.generateUserId(30));
		userEntity.setEncryptedpassword(bCrypt.encode(userEntity.getPassword()));
		userEntity.setAddresses(getAddress());
		userEntity.setRoleEntity(Arrays.asList(adminRole));
		userRepository.save(userEntity);

	}

	private AuthoritiesEntity createReadWriteDeleteOperation(String name) {

		AuthoritiesEntity authoritiesEntity = authorityRepository.findByName(name);
		if (authoritiesEntity == null) {
			AuthoritiesEntity auth = new AuthoritiesEntity();
			auth.setName(name);
			AuthoritiesEntity value = authorityRepository.save(auth);
			return value;
		}
		return authoritiesEntity;
	}

	private RoleEntity createRole(String name, Collection<AuthoritiesEntity> authorities) {

		RoleEntity roleEntity = roleRepository.findByName(name);
		if (roleEntity == null) {

			RoleEntity role = new RoleEntity();
			role.setName(name);
			role.setAuthortiesEntity(authorities);
			RoleEntity value = roleRepository.save(role);
			return value;
		}
		return roleEntity;
	}

	private List<AddressEntity> getAddress() {

		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setAddressId(utility.generateAddressId(30));
		addressEntity.setCity("Haridwar");
		addressEntity.setCountry("INDIA");
		addressEntity.setPostalcode("655282");
		addressEntity.setStreetName("Kochin Gali");
		addressEntity.setType("Billing");
		addressEntity.setUserEntity(userEntity);
		List<AddressEntity> list = new ArrayList<>();
		list.add(addressEntity);
		return list;

	}
}
