package com.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.restful.entity.PasswordResetEntity;
import com.restful.entity.UserEntity;

@Repository
@Transactional
public interface PasswordResetRespository extends JpaRepository<PasswordResetEntity, Long> {
	
	public PasswordResetEntity findByToken(String token);

}
