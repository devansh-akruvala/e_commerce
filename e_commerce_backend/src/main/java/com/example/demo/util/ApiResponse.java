package com.example.demo.util;

import java.time.LocalDateTime;

public class ApiResponse {
	
	private final boolean success;
	private final Object object;
	
	
	
	public ApiResponse(boolean success, Object object) {
		super();
		this.success = success;
		this.object = object;
	}



	public boolean isSuccess() {
		return success;
	}

	public Object getObject() {
		return object;
	}


	public String getTimeStamp() {
		return LocalDateTime.now().toString();
	}
}
