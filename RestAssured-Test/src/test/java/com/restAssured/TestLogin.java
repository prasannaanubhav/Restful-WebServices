package com.restAssured;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

class TestLogin {

	private static final String JSON = "application/json";

	@BeforeEach
	void setUp() throws Exception {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8081;
	}

	@Test
	void test() {
		
		Map<String, String> loginDetails = new HashMap<>();
		loginDetails.put("email", "RamVilas@paswan.com");
		loginDetails.put("password", "ramujipaswan");
		
		Response response = given().contentType(JSON).accept("application/json").body(loginDetails).when().post("/login").then().statusCode(200)
				.extract().response();
	String authHeader =	response.header("Authorization");
	assertNotNull(authHeader);
	System.out.println(authHeader);
	String userID = response.getHeader("UserId");
	System.out.println(userID);
	assertNotNull(userID);
	}

}
