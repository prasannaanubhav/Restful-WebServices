package com.restful.security;

import com.restful.SpringApplicationContext;

public class SecurityConstants {

	public static final long EXPIRATION_TIME = 864000000; // 10 days
	public static final long EMAIL_EXPIRATION_TIME=300000;// 5 minutes
	public static final long PASSWORD_EXPIRATION_TIME = 60000*60*24;//1 day
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/restful";
	public static final String EMAIL_VERIFICATION_TOKEN = "/restful/email-verification";
	public static final String PASSOWRD_VERIFICATION_TOKEN = "/restful/password-reset";
	public static final String PASSWORD_RESET="/restful/password-verification";

	public static String getTokenSecret() {

		ApplicationProperties app = (ApplicationProperties) SpringApplicationContext.getBean("ApplicationProperties");
		return app.getToken();
	}

}
