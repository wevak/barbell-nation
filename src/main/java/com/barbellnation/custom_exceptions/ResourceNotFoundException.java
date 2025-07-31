package com.barbellnation.custom_exceptions;

public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(String err) {
		super(err);
	}
}
