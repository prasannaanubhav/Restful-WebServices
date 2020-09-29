package com.restful.NativeQuery;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.restful.entity.UserEntity;
import com.restful.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class NativeQueryTest {

	@Autowired
	UserRepository userRepository;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		Pageable pageable = PageRequest.of(0, 2);
		Page<UserEntity> page = userRepository.getUserByPagination(pageable);
		List<UserEntity> list = page.getContent();

		assertNotNull(list);

	}

	@Test
	void testGetUserByfirstname() {

		List<UserEntity> list = userRepository.getUserByfirstname("Anil");
		assertNotNull(list.get(0).getFirstname());

	}

	@Test
	void getUserByLastname() {

		List<UserEntity> list = userRepository.getUserByLastname("Ambani");
		assertEquals("Ambani", list.get(0).getLastname());
	}

	@Test
	void getUserByKeyword() {

		List<UserEntity> list = userRepository.getUserByKeyword("An");
		assertNotNull(list);
	}

	@Test
	void getFirstNadLast() {

		List<Object[]> list = userRepository.getFirstNadLast("Anil", "Ambani");

		Object[] user = list.get(1);
		String firstname = String.valueOf(user[0]);
		String lastname = String.valueOf(user[1]);
		System.out.println(firstname);
		System.out.println(lastname);
		assertNotNull(list);
	}

	@Test
	void UpdateEmaiTokenStatus() {

		String userId = "IbqOz0Q6kaU0IpHIl1VqocZOLru8Y5";
	    userRepository.UpdateEmaiTokenStatus(true, userId);
	   UserEntity userEntity= userRepository.findByUserId(userId);
	   assertEquals(true, userEntity.getEmailVerificationStatus());

	}
	
	@Test
	void getLimitedObjects() {
		String userId = "IbqOz0Q6kaU0IpHIl1VqocZOLru8Y5";
	    List<Object[]> record = userRepository.getLimitedObjects(userId);
	    Object[] value = record.get(0);
	    String firstname = String.valueOf(value[0]);
	    String lastname = String.valueOf(value[1]);
	    String email = String.valueOf(value[2]);
	    
	    assertNotNull(firstname);
	    assertNotNull(lastname);
	    assertNotNull(email);
		
	}

}
