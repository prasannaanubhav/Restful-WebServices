//package com.restful.web;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.http.ResponseEntity;
//
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.when;
//
//import com.restful.dto.AddressDto;
//import com.restful.dto.UserDto;
//import com.restful.service.impl.UserServiceImpl;
//import com.restful.userResponse.UserResponse;
//
//class ControllerTest {
//
//	@InjectMocks
//	Controller controller;
//
//	@Mock
//	UserServiceImpl userServiceImpl;
//
//	UserDto userDto;
//
//	@BeforeEach
//	void setUp() throws Exception {
//
//		userDto = new UserDto();
//		userDto.setFirstname("Anubhav");
//		userDto.setLastname("Prasanna");
//		userDto.setEmail("prasannaanybhav@gmail.com");
//		userDto.setAddresses(getAddressList());
//		
//	}
//
//	@Test
//	final void testGetUserByUserId() {
//		try {
//			when(userServiceImpl.getUserByUserId(anyString())).thenReturn(userDto);
//		} catch (NullPointerException e) {
//			System.out.println(e.getMessage());
//		}
//		ResponseEntity<UserResponse> userResponse = controller.getUserByUserId("hello");
//		UserResponse resp = userResponse.getBody();
//		assertNotNull(resp);
//		assertEquals(resp.getFirstname(), userDto.getFirstname());
//
//	}
//
//	private List<AddressDto> getAddressList() {
//
//		AddressDto shippingDto = new AddressDto();
//		shippingDto.setCity("patna");
//		shippingDto.setCountry("India");
//		shippingDto.setUserDto(userDto);
//
//		AddressDto billlingDto = new AddressDto();
//		billlingDto.setCity("Pune");
//		billlingDto.setCountry("India");
//		billlingDto.setUserDto(userDto);
//
//		List<AddressDto> lst = new ArrayList<AddressDto>();
//		lst.add(billlingDto);
//		lst.add(shippingDto);
//		return lst;
//	}
//
//}
