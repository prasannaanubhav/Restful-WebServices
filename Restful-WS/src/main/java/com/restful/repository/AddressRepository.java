package com.restful.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.restful.entity.AddressEntity;
import com.restful.entity.UserEntity;

@Repository
@Transactional
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
	
	public List<AddressEntity> findAllByUserEntity(UserEntity userEntity);
	public AddressEntity findByAddressId(String addressId);

}
