package com.restful.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.restful.entity.UserEntity;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	public UserEntity findByEmail(String email);

	public UserEntity findByUserId(String UserId);

	public UserEntity findByEmailVerificationToken(String token);

	//*********** SQL NATIVE QUERY *******************//
	
	@Query(value = "select*from userdetails u where u.email_verification_status= 'true'", countQuery = "select count(*) from userdetails u where u.email_verification_status ='true'", nativeQuery = true)
	public Page<UserEntity> getUserByPagination(Pageable pageable);

	@Query(value = "select*from userdetails u where u.firstname=?1", nativeQuery = true)
	public List<UserEntity> getUserByfirstname(String firstname);

	@Query(value = "select*from userdetails u where u.lastname=:LastName", nativeQuery = true)
	public List<UserEntity> getUserByLastname(@Param("LastName") String lastname);

	@Query(value = "select*from userdetails u where u.firstname LIKE %:keyword% and u.lastname LIKE %:keyword% ", nativeQuery = true)
	public List<UserEntity> getUserByKeyword(@Param("keyword") String Keyword);

	@Query(value = "select u.firstname, u.lastname from userdetails u where u.firstname=:first and u.lastname=:last", nativeQuery = true)
	public List<Object[]> getFirstNadLast(@Param("first") String firstname, @Param("last") String lastname);
	
	@Modifying 
	@Query(value="update userdetails u set u.email_verification_status=:value where user_id=:userId",nativeQuery = true)
	public void UpdateEmaiTokenStatus(@Param("value")Boolean value,@Param("userId") String userId);
	
	//************JPQL*************//
	@Query("select u.firstname, u.lastname, u.email from UserEntity u where u.userId=:userId")
	public List<Object[]> getLimitedObjects(@Param("userId")String userId);
	
}
