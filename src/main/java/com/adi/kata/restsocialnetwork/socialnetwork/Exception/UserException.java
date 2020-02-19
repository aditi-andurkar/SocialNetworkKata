package com.adi.kata.restsocialnetwork.socialnetwork.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserException extends Exception {
	
	private String errorMessage;
	public UserException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		// TODO Auto-generated constructor stub
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public UserException() {
		super();
	}

	
}
