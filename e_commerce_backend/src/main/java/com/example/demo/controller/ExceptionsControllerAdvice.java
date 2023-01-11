package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.exceptions.AuthenticationFailedException;
import com.example.demo.exceptions.CustomExceptions;
import com.example.demo.exceptions.ProductNotExistException;

@ControllerAdvice
public class ExceptionsControllerAdvice {

	@ExceptionHandler(value = CustomExceptions.class)
	public final ResponseEntity<String> handleCustomException(CustomExceptions customExceptions){
		return new ResponseEntity<>(customExceptions.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = AuthenticationFailedException.class)
	public final ResponseEntity<String> handleAuthenticationFailedException(AuthenticationFailedException authenticationFailedException){
		return new ResponseEntity<>(authenticationFailedException.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = ProductNotExistException.class)
	public final ResponseEntity<String> handleProductNotExistException(ProductNotExistException productNotExistException){
		return new ResponseEntity<>(productNotExistException.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
