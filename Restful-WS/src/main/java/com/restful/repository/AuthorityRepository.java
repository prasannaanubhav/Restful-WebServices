package com.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.restful.entity.AuthoritiesEntity;

@Repository
@Transactional
public interface AuthorityRepository extends JpaRepository<AuthoritiesEntity, Long> {
	
	public AuthoritiesEntity findByName(String name);

}
