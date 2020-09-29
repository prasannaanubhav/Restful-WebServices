package com.restful.exceptions;

import java.io.Serializable;
import java.util.Date;

public class ErrorException implements Serializable{

	private static final long serialVersionUID = -8596189355597916143L;
	private String exceptionMessage;
	private Date timestamp;

	public ErrorException(String exceptionMessage, Date timestamp) {

		this.exceptionMessage = exceptionMessage;
		this.timestamp = timestamp;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	

}
