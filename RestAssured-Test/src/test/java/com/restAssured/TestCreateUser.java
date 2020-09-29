package com.restAssured;

import static io.restassured.RestAssured.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

class TestCreateUser {

	public final String ContextPath = "/restful";

	@BeforeEach
	void setUp() throws Exception {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8081;
	}

	@Test
	void testCreateUser() throws JSONException {

		List<Map<String, Object>> lst = new ArrayList<>();
		Map<String, Object> addresses = new HashMap<>();
		addresses.put("city", "Patna");
		addresses.put("country", "India");
		addresses.put("streetName", "Hinjewadi");
		addresses.put("postalcode", "411056");
		addresses.put("type", "Billing");
		lst.add(addresses);

		Map<String, Object> userDetails = new HashMap<>();
		userDetails.put("firstname", "RamVilas");
		userDetails.put("lastname", "Paswan");
		userDetails.put("email", "RamVilas@paswan.com");
		userDetails.put("password", "ramujipaswan");
		userDetails.put("addresses", lst);

		Response response = given().contentType("application/json").accept("application/json").body(userDetails).when()
				.post(ContextPath).then().statusCode(200).contentType("application/json").extract().response();

		String userId = response.jsonPath().getJsonObject("userId");
		System.out.println(userId);
		assertNotNull(userId);
		List<Object> address = response.jsonPath().getList("addresses");
		System.out.println(address);
		assertTrue(address.size()==1);
	   JSONArray json = new JSONArray(address.toArray());
	   String city = json.getJSONObject(0).getString("city");
	   System.out.println(city);
       assertEquals("Patna", city);
	}

}
