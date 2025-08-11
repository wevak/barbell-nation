package com.barbellnation.dto;

public class AuthResponse {
	private String message;
	private String jwt;
	private Long ownerId;
	
	public AuthResponse(String message, String jwt, Long ownerId) {
		super();
		this.message = message;
		this.jwt = jwt;
		this.ownerId = ownerId;
	}
	
	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
	
}
