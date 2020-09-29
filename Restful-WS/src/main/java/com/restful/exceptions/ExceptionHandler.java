package com.restful.exceptions;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
@ControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = {UserServiceException.class})
	public ResponseEntity<Object> getHandledExcpetion(UserServiceException userException){
		
		ErrorException errorException = new ErrorException(userException.getMessage(),new Date());
		
		return new ResponseEntity<>(errorException, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
