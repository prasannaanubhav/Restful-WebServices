package com.restful.service.impl;

import org.junit.jupiter.api.BeforeEach;



import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.restful.amazon.AmazonSes;
import com.restful.dto.AddressDto;
import com.restful.dto.UserDto;
import com.restful.entity.UserEntity;
import com.restful.repository.UserRepository;
import com.restful.security.Utility;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Mock
	UserRepository userRepository;
	
	@Mock
	Utility utility;

	@Mock
	BCryptPasswordEncoder bCrypt;
	
	@Mock
	AmazonSes amazonSes;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	final void testGetUser() {

		UserEntity userEntity = new UserEntity();
		userEntity.setFirstname("hello");
		userEntity.setLastname("world");
		when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
		UserDto userDto = userServiceImpl.getUser("anything");
		assertNotNull(userDto);
		assertEquals("hello", userDto.getFirstname());
		
	}
	
	@Test
	final void testCreateUser() {
	
		UserEntity userEntity = new UserEntity();
		userEntity.setFirstname("Hello");
		userEntity.setLastname("world");
		when(userRepository.findByEmail(anyString())).thenReturn(null);
		when(utility.generateUserId(anyInt())).thenReturn("userId");
		when(bCrypt.encode(anyString())).thenReturn("sahsfnsahva");
		when(utility.generateEmailToken(anyString())).thenReturn("asdajsdgsdsaydbhsad");
		when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
		Mockito.doNothing().when(amazonSes).verifyEmail(any(UserDto.class));
			
		UserDto userDto = new UserDto();
		userDto.setFirstname("Hello");
		userDto.setLastname("world");
		
		AddressDto addressDto=new AddressDto();
		addressDto.setType("Billing");
		addressDto.setUserDto(userDto);
		
		List<AddressDto> lst  = new ArrayList<>();
		lst.add(addressDto);
		userDto.setAddresses(lst);
		
		UserDto storedUser= userServiceImpl.createUser(userDto);
		assertNotNull(storedUser);
		assertEquals(storedUser.getFirstname(), userDto.getFirstname());
	}

}
