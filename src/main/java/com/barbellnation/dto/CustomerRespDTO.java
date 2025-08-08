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
	private Long customerId;
	private String name;
	private String email;
	private String phone;
	private String gender;
	private Long packageId;
}
