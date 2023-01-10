package com.example.demo.exceptions;

public class CustomExceptions extends IllegalArgumentException{
	public CustomExceptions(String msg) {
		super(msg);
	}
}
