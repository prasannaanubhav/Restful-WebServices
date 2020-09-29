package com.restful.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.restful.amazon.AmazonSes;
import com.restful.dto.UserDto;
import com.restful.entity.AddressEntity;
import com.restful.entity.PasswordResetEntity;
import com.restful.entity.UserEntity;
import com.restful.exceptions.MessageException;
import com.restful.exceptions.UserServiceException;
import com.restful.repository.PasswordResetRespository;
import com.restful.repository.UserRepository;
import com.restful.security.Utility;
import com.restful.service.UserService;
import com.restful.userRequest.UserLoginRequest;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Utility utility;

	@Autowired
	private BCryptPasswordEncoder bCrypt;

	@Autowired
	private PasswordResetRespository passwordResetRepository;
	
	@Autowired
	private AmazonSes amazonSes;

	@Override
	public UserDto createUser(UserDto userDto) {

		UserEntity userEnt = userRepository.findByEmail(userDto.getEmail());
		if (userEnt != null)
			throw new RuntimeException("User Already exist");

		ModelMapper modelmapper = new ModelMapper();
		UserEntity userEntity = modelmapper.map(userDto, UserEntity.class);

		for (int i = 0; i < userEntity.getAddresses().size(); i++) {

			AddressEntity addEntity = userEntity.getAddresses().get(i);
			addEntity.setAddressId(utility.generateAddressId(20));
			addEntity.setUserEntity(userEntity);
			userEntity.getAddresses().set(i, addEntity);

		}
		userEntity.setUserId(utility.generateUserId(30));
		userEntity.setEncryptedpassword(bCrypt.encode(userDto.getPassword()));
		userEntity.setEmailVerificationToken(utility.generateEmailToken(utility.generateUserId(30)));
		userEntity.setEmailVerificationStatus(false);
		UserEntity entity = userRepository.save(userEntity);
		UserDto dto = null;
		try {
			dto = modelmapper.map(entity, UserDto.class);
			amazonSes.verifyEmail(dto);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UserServiceException(email);
		UserPrinciple userPrinciple = new UserPrinciple(userEntity);
		return userPrinciple;
	}

	@Override
	public UserDto getUser(String email) {
		UserDto userDto = new UserDto();
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UserServiceException(email);
		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}

	@Override
	public UserDto getUserByUserId(String UserId) {

		UserDto userDto = new UserDto();
		try {
		UserEntity userEntity = userRepository.findByUserId(UserId);
		BeanUtils.copyProperties(userEntity, userDto);
		
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return userDto;
	}

	@Override
	public UserDto updateUser(String UserId, UserDto userDto) {
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(UserId);
		if (userEntity == null)
			throw new UserServiceException(MessageException.NO_RECORD_FOUND);
		userEntity.setFirstname(userDto.getFirstname());
		userEntity.setLastname(userDto.getLastname());
		userEntity.setEmail(userDto.getEmail());
		userEntity.setPassword(userDto.getPassword());
		UserEntity ue = userRepository.save(userEntity);
		BeanUtils.copyProperties(ue, returnValue);
		return returnValue;
	}

	@Override
	public String deleteUser(String UserId) {
		UserEntity userEntity = userRepository.findByUserId(UserId);
		if (userEntity == null)
			throw new UserServiceException(MessageException.NO_RECORD_FOUND);
		userRepository.delete(userEntity);
		return null;
	}

	@Override
	public List<UserDto> getUserList(int page, int limit) {

		List<UserDto> arrlist = new ArrayList<>();
		if (page > 0)
			page = page - 1; // always take pagination from zero remember that.
		Pageable pageable = PageRequest.of(page, limit);
		try {
			Page<UserEntity> list = userRepository.findAll(pageable);
			List<UserEntity> userList = list.getContent();
			if (userList == null)
				throw new UserServiceException(MessageException.NO_RECORD_FOUND);
			for (UserEntity ue : userList) {
				UserDto dto = new UserDto();
				BeanUtils.copyProperties(ue, dto);
				arrlist.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage() + "........" + e.getCause());
		}

		return arrlist;
	}

	@Override
	public Boolean verifyEmailToken(String token) {

		Boolean returnvalue = false;
		UserEntity userEntity = new UserEntity();
		try {
			userEntity = userRepository.findByEmailVerificationToken(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (userEntity != null) {

			returnvalue = utility.notExpired(token);
			if (returnvalue) {
				userEntity.setEmailVerificationToken(null);
				userEntity.setEmailVerificationStatus(true);
				userRepository.save(userEntity);
				returnvalue = true;
			}
		}
		return returnvalue;
	}

	@Override
	public Boolean passwordResetToken(String email) {

		Boolean returnValue = false;
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity != null) {

			PasswordResetEntity passwordResetEntity = new PasswordResetEntity();
			String token = utility.generatePasswordToken(userEntity.getUserId());
			passwordResetEntity.setToken(token);
			passwordResetEntity.setUserEntity(userEntity);
			passwordResetRepository.save(passwordResetEntity);
			ModelMapper modelmapper = new ModelMapper();
			UserDto userDto = modelmapper.map(userEntity, UserDto.class);
			new AmazonSes().sendEmailForPasswordReset(userDto, token);
			returnValue = true;
		}

		return returnValue;
	}

	@Override
	public Boolean passwordResetConfirmation(String token,UserLoginRequest userLoginRequest) {
		Boolean returnValue = false;
		try {
			PasswordResetEntity passwordResetEntity= passwordResetRepository.findByToken(token);
			UserEntity userEntity = passwordResetEntity.getUserEntity();
		
		if(userEntity!=null) {
			userEntity.setPassword(userLoginRequest.getPassword());
			userRepository.save(userEntity);
			passwordResetEntity.setToken(null);
			passwordResetRepository.save(passwordResetEntity);
			returnValue=true;
		}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return returnValue;
	}

}
