package com.restful.web;

import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restful.dto.AddressDto;
import com.restful.dto.UserDto;
import com.restful.exceptions.MessageException;
import com.restful.exceptions.UserServiceException;
import com.restful.service.AddressService;
import com.restful.service.UserService;
import com.restful.userRequest.PasswordReset;
import com.restful.userRequest.UserLoginRequest;
import com.restful.userRequest.UserRequest;
import com.restful.userResponse.AddressResponse;
import com.restful.userResponse.UserResponse;

@SuppressWarnings("deprecation")
@RestController
@RequestMapping(value = "/restful")
public class Controller {

	@Autowired
	private UserService userService;

	@Autowired
	private AddressService addressService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) throws UserServiceException {

		if (userRequest.getFirstname().isEmpty()) {
			throw new UserServiceException(MessageException.MISSING_REQUIRED_FIELD);
		}

		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userRequest, UserDto.class);
		UserDto dto = userService.createUser(userDto);
		UserResponse userResponse = modelMapper.map(dto, UserResponse.class);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}

	@GetMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> getUserByUserId(@PathVariable("id") String userId) {

		UserResponse userResponse = new UserResponse();
		UserDto userDto = userService.getUserByUserId(userId);
		BeanUtils.copyProperties(userDto, userResponse);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}

	@PutMapping(path = "/{UserId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> updateUserDetails(@RequestBody UserRequest userRequest,
			@PathVariable("UserId") String UserId) {

		UserResponse userResponse = new UserResponse();
		UserDto userDto = new UserDto();
		if (userRequest.getFirstname().isEmpty() || userRequest.getLastname().isEmpty()
				|| userRequest.getEmail().isEmpty() || userRequest.getPassword().isEmpty())
			throw new UserServiceException(MessageException.MISSING_REQUIRED_FIELD);
		BeanUtils.copyProperties(userRequest, userDto);
		UserDto dto = userService.updateUser(UserId, userDto);
		BeanUtils.copyProperties(dto, userResponse);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);

	}

	@DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String deleteUser(@PathVariable("id") String id) {

		if (id.isEmpty())
			throw new UserServiceException(MessageException.MISSING_REQUIRED_FIELD);
		userService.deleteUser(id);
		return "USER DELETED";

	}

	@GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserResponse>> getListOfUsers(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "25") int limit) {

		List<UserResponse> userList = new ArrayList<>();
		List<UserDto> dtoList = userService.getUserList(page, limit);
		for (UserDto userDto : dtoList) {
			UserResponse response = new UserResponse();
			BeanUtils.copyProperties(userDto, response);
			userList.add(response);
		}

		return new ResponseEntity<List<UserResponse>>(userList, HttpStatus.OK);
	}

	@GetMapping(path = "/users/{id}/address", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AddressResponse>> getListOfAdresses(@PathVariable("id") String userId) {

		ModelMapper modelMapper = new ModelMapper();
		List<AddressResponse> addressResponse = new ArrayList<>();
		List<AddressDto> listDto = addressService.getAddressList(userId);

		Type listType = new TypeToken<List<AddressResponse>>() {
		}.getType();
		addressResponse = modelMapper.map(listDto, listType);

		for (AddressResponse responseList : addressResponse) {
			Link selflink = ControllerLinkBuilder
					.linkTo(ControllerLinkBuilder.methodOn(Controller.class).getUserByUserId(userId))
					.withRel("getUser");
			responseList.add(selflink);
		}

		return new ResponseEntity<List<AddressResponse>>(addressResponse, HttpStatus.OK);

	}

	@GetMapping(path = "/users/address/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AddressResponse> getAddress(@PathVariable("id") String addressId) {

		AddressDto addressDto = addressService.getAddress(addressId);
		ModelMapper modelMapper = new ModelMapper();
		Link addressLink = ControllerLinkBuilder.linkTo(Controller.class).slash("users").slash("address")
				.slash(addressId).withSelfRel();
		AddressResponse addressResponse = modelMapper.map(addressDto, AddressResponse.class);
		addressResponse.add(addressLink);
		return new ResponseEntity<AddressResponse>(addressResponse, HttpStatus.OK);
	}

	@GetMapping(path = "/email-verification")
	public ResponseEntity<String> verifyEmailToken(@RequestParam("token") String token) {

		Boolean returnvalue = false;

		returnvalue = userService.verifyEmailToken(token);
		if (returnvalue) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Token Has Expired", HttpStatus.OK);
	}

	@PostMapping(path = "/password-reset")
	public ResponseEntity<String> passwordReset(@RequestBody PasswordReset passwordReset) {
		Boolean returnValue = false;
		returnValue = userService.passwordResetToken(passwordReset.getEmail());
		if (returnValue) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}
		return new ResponseEntity<String>("UNSUCCESSFULL", HttpStatus.OK);
	}

	@PostMapping(path = "/password-verification")
	public ResponseEntity<String> passwordResetConfirmation(@RequestBody UserLoginRequest userLoinRequest,
			@RequestParam("token") String token) {
		Boolean returnValue = false;
		returnValue = userService.passwordResetConfirmation(token, userLoinRequest);
		if (returnValue) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}
		return new ResponseEntity<String>("UNSUCCESSFULL", HttpStatus.OK);

	}
}
