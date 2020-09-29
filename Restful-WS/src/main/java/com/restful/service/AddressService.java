package com.restful.service;

import java.util.List;

import com.restful.dto.AddressDto;

public interface AddressService {

	public List<AddressDto> getAddressList(String userId);
	public AddressDto getAddress(String addressId);
}
