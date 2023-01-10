package com.example.demo.exceptions;


public class AuthenticationFailedException extends IllegalArgumentException{
	public AuthenticationFailedException(String msg) {
		super(msg);
	}
}
