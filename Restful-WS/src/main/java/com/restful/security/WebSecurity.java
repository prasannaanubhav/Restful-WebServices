package com.restful.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.restful.repository.UserRepository;
import com.restful.service.UserService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private final UserService userDetailsService;
	private final BCryptPasswordEncoder bCryptPassowrdEncoder;
	private final UserRepository userRepository;

	public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPassowrdEncoder,UserRepository userRepository) {

		this.userDetailsService = userDetailsService;
		this.bCryptPassowrdEncoder = bCryptPassowrdEncoder;
		this.userRepository = userRepository;
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPassowrdEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL)
				.permitAll().
				antMatchers(HttpMethod.GET, SecurityConstants.EMAIL_VERIFICATION_TOKEN)
				.permitAll().
				antMatchers(HttpMethod.POST, SecurityConstants.PASSOWRD_VERIFICATION_TOKEN)
				.permitAll().
				antMatchers(HttpMethod.POST, SecurityConstants.PASSWORD_RESET)
				.permitAll()
				.antMatchers("/v2/api-docs","/configuration/**","/swagger*/**","/webjars/**")
				.permitAll()
				.antMatchers(HttpMethod.DELETE, "/restful/**").hasRole("ADMIN")
				.anyRequest().authenticated().and().addFilter(new AuthFilter(authenticationManager()))
				.addFilter(new AuthorizationFilter(authenticationManager(), userRepository)).sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS); // it will create the session stateless and
																			// will not allow to cache the headers.
	}

}
