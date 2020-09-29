package com.restful.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.restful.dto.UserDto;
import com.restful.userRequest.UserLoginRequest;

public interface UserService extends UserDetailsService{

	public UserDto createUser(UserDto userDto);
	public UserDto getUser(String email);
	public UserDto getUserByUserId(String UserId);
	public UserDto updateUser(String UserId,UserDto userDto);
	public String deleteUser(String UserId);
	public List<UserDto> getUserList(int page, int limit);
	public Boolean verifyEmailToken(String token);
	public Boolean passwordResetToken(String email);
	public Boolean passwordResetConfirmation(String token,UserLoginRequest userLoginRequest);
}
