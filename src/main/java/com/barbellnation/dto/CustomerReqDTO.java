package com.barbellnation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerReqDTO {
	@NotBlank(message = "package id is required")
	private Long packageId;
	
	@NotBlank(message = "first name is required !!!")
	private String name;
	
	@Email(message = "invalid email format !")
	private String email;
	
	@NotBlank(message = "phone is required !!!")
	private String phone;
	
	@NotBlank(message = "gender is required !!!")
	private String gender;
	

}
