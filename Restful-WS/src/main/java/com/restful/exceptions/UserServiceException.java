package com.restful.exceptions;

public class UserServiceException extends RuntimeException {

	private static final long serialVersionUID = -4278667107341396378L;

	public UserServiceException(String message) {
		super(message);

	}

}
