package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.exceptions.AuthenticationFailedException;
import com.example.demo.exceptions.CustomExceptions;

@ControllerAdvice
public class ExceptionsControllerAdvice {

	@ExceptionHandler(value = CustomExceptions.class)
	public final ResponseEntity<String> handleCustomException(CustomExceptions customExceptions){
		return new ResponseEntity<>(customExceptions.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = AuthenticationFailedException.class)
	public final ResponseEntity<String> handleCustomException(AuthenticationFailedException authenticationFailedException){
		return new ResponseEntity<>(authenticationFailedException.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
