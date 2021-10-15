package com.example.bvm.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomResponse extends ResponseEntity{
	private Object Value;
	private Object error;
	private int status;
	private String message;
	



	public CustomResponse(HttpStatus http_status, Object value, Object error, int status, String message) {
		super(http_status);
		Value = value;
		this.error = error;
		this.status = status;
		this.message = message;
	}

	
}
