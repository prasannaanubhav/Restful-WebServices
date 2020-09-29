package com.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restful.dto.AddressDto;
import com.restful.entity.AddressEntity;
import com.restful.entity.UserEntity;
import com.restful.exceptions.MessageException;
import com.restful.exceptions.UserServiceException;
import com.restful.repository.AddressRepository;
import com.restful.repository.UserRepository;
import com.restful.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<AddressDto> getAddressList(String userId) {

		List<AddressDto> returnValue = new ArrayList<AddressDto>();
		ModelMapper modelMapper = new ModelMapper();
		UserEntity entity = userRepository.findByUserId(userId);
		if (entity == null)
			throw new UserServiceException(MessageException.NO_RECORD_FOUND);

		List<AddressEntity> listEntity = addressRepository.findAllByUserEntity(entity);

		for (AddressEntity addressEntity : listEntity) {
			returnValue.add(modelMapper.map(addressEntity, AddressDto.class));
		}

		return returnValue;
	}

	@Override
	public AddressDto getAddress(String addressId) {

		ModelMapper modelMapper = new ModelMapper();
		AddressEntity addressEntity = addressRepository.findByAddressId(addressId);
		AddressDto dto = modelMapper.map(addressEntity, AddressDto.class);
		return dto;
	}

}
