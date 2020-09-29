package com.restAssured;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class RestApiFunctionTest {

	private static String authHeader;
	private static String userID;
	private static final String JSON = "application/json";
	@BeforeEach
	void setUp() throws Exception {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8081;
	}

	@Test
	void a() {
		Map<String, String> loginDetails = new HashMap<>();
		loginDetails.put("email", "RamVilas@paswan.com");
		loginDetails.put("password", "ramujipaswan");

		Response response = given().contentType(JSON).accept("application/json").body(loginDetails).when()
				.post("/login").then().statusCode(200).extract().response();
	    authHeader = response.header("Authorization");
		assertNotNull(authHeader);
		System.out.println(authHeader);
		userID = response.getHeader("UserId");
		System.out.println(userID);
		assertNotNull(userID);
	}

	@Test
	void b() {
		
		Response response = given().pathParam("userID", userID)
		.header("Authorization",authHeader)
		.contentType(JSON).accept(JSON).when().get("/restful/{userID}").then().statusCode(200).extract().response();
		
		String firstname = response.jsonPath().getJsonObject("firstname");
		assertEquals(firstname, "RamVilas");
		String lastname = response.jsonPath().getJsonObject("lastname");
		assertEquals(lastname, "Paswan");
		List<Object> list = response.jsonPath().getList("addresses");
		JSONArray arr;
		try {
			arr = new JSONArray(list.toArray());
			String city = arr.getJSONObject(0).getString("city");
			assertEquals(city, "Patna");
			assertTrue(arr.length()==1);
		} catch (JSONException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	
}
