package com.jb.springbootrestapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jb.springbootrestapi.exception.ValidationException;

@ControllerAdvice
@RestController
public class ControllerExceptionHanlerTheThierd {
	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDetails handleValidationException(ValidationException exception) {
		ErrorDetails details = new ErrorDetails();
		details.setCode(HttpStatus.BAD_REQUEST.value());
		details.setMessage("Validation exception");
		return details;
		
	}
	
	

}
