package com.barbellnation.dto;

import java.time.LocalDateTime;

public class ApiResponse {
//	private LocalDateTime timeStamp;
	private String message;
	public ApiResponse(String message) {
		super();
		this.message = message;
//		this.timeStamp=LocalDateTime.now();
	}
	
	public ApiResponse() {
		super();
	}
	
//	public LocalDateTime getTimeStamp() {
//		return timeStamp;
//	}
//	public void setTimeStamp(LocalDateTime timeStamp) {
//		this.timeStamp = timeStamp;
//	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
