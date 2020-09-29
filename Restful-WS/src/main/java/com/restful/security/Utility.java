package com.restful.security;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class Utility {

	private Random random = new SecureRandom();
	private String Alpabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	public String generateUserId(int length) {

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {

			sb.append(Alpabet.charAt(random.nextInt(Alpabet.length())));
		}
		return sb.toString();
	}

	public String generateAddressId(int length) {

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {

			sb.append(Alpabet.charAt(random.nextInt(Alpabet.length())));
		}
		return sb.toString();
	}

	public Boolean notExpired(String token) {
	
			Claims claims = Jwts.parser().setSigningKey(SecurityConstants.getTokenSecret()).parseClaimsJws(token)
					.getBody();
			Date tokenExpirationDate = claims.getExpiration();
		Date todaysDate = new Date();
		return todaysDate.before(tokenExpirationDate);
	}

	public String generateEmailToken(String userId) {

		String token = Jwts.builder().setSubject(userId)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EMAIL_EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret()).compact();

		return token;
	}
	
	public String generatePasswordToken(String userId) {
		
		String token = Jwts.builder().setSubject(userId)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.PASSWORD_EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret()).compact();

		return token;
		
		
	}

}
