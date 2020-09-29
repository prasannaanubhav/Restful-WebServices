package com.restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.restful.security.ApplicationProperties;

@SpringBootApplication
@ComponentScan(basePackages = "com.restful")
public class RestfulWsApplication extends SpringBootServletInitializer{
	
	@Override
	protected  SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RestfulWsApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(RestfulWsApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder getEncryptedPassword() {
		
		return new BCryptPasswordEncoder ();
	}
	
	@Bean
	public SpringApplicationContext springApplicationContext() {
		
		return new SpringApplicationContext();
	}
	
	@Bean(name= "ApplicationProperties")
	public ApplicationProperties getAppProperties() {
		
		return new ApplicationProperties();
	}
}
