package com.restful.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.restful.entity.UserEntity;
import com.restful.repository.UserRepository;
import com.restful.service.impl.UserPrinciple;

import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter {
	
	private final UserRepository userRepository;

	public AuthorizationFilter(AuthenticationManager authenticationManager,UserRepository userRepository) {
		super(authenticationManager);
		this.userRepository=userRepository;

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
					throws IOException, ServletException {
		
		String header = request.getHeader(SecurityConstants.HEADER_STRING);
		
		if(header!=null && !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
	
		UsernamePasswordAuthenticationToken authtoken =  getAuthToken(request);
		SecurityContextHolder.getContext().setAuthentication(authtoken);
		chain.doFilter(request, response);
		
	}

	private UsernamePasswordAuthenticationToken getAuthToken(HttpServletRequest request) {

		String token = request.getHeader(SecurityConstants.HEADER_STRING);
		if (token != null) {

			token = token.replace(SecurityConstants.TOKEN_PREFIX, "");
			String user = Jwts.parser().setSigningKey(SecurityConstants.getTokenSecret()).parseClaimsJws(token).getBody()
					.getSubject();
			if(user!=null) {
				UserEntity userEntity = userRepository.findByEmail(user);
				UserPrinciple userPrinciple = new UserPrinciple(userEntity);
				return new UsernamePasswordAuthenticationToken(user, null, userPrinciple.getAuthorities());
			}
			return null;
		}

		return null;
	}

}
