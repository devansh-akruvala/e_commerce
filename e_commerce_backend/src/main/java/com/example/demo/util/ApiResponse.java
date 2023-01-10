package com.example.demo.util;

import java.time.LocalDateTime;

public class ApiResponse {
	
	private final boolean success;
	private final Object response;
	
	
	
	public ApiResponse(boolean success, Object object) {
		super();
		this.success = success;
		this.response = object;
	}



	public boolean isSuccess() {
		return success;
	}

	public Object getResponse() {
		return response;
	}


	public String getTimeStamp() {
		return LocalDateTime.now().toString();
	}
}
