package com.barbellnation.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthRequest {
	@NotBlank
	private String email;
	@NotBlank
	private String password;
	
	public AuthRequest() {
		super();
	}

	public AuthRequest(@NotBlank String email, @NotBlank String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "AuthRequest [email=" + email + ", password=" + password + "]";
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
