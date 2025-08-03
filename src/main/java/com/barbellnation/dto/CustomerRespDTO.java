package com.barbellnation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomerRespDTO {
	@NotBlank(message = "package id is required")
	private Long customerId;
	
	@NotBlank(message = "first name is required !!!")
	private String name;
	
	@Email(message = "invalid email format !")
	private String email;
	
	@NotBlank(message = "phone is required !!!")
	private String phone;
	
	@NotBlank(message = "gender is required !!!")
	private String gender;
}
