package com.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.restful.entity.RoleEntity;

@Repository
@Transactional
public interface Rolerepository extends JpaRepository<RoleEntity, Long> {
	
	public RoleEntity findByName(String name);

}
