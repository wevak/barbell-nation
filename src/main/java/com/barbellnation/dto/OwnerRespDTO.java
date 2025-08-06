package com.barbellnation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OwnerRespDTO {
	@NotNull(message = "owner id is required")
	private Long ownerId;
	
	@NotBlank(message = "first name is required !!!")
	private String name;
	
	@Email(message = "invalid email format !")
	private String email;
	
	@NotBlank(message = "phone is required !!!")
	private String phone;
	
	@NotBlank(message = "gender is required !!!")
	private String gender;
	
	@NotBlank(message = "gym type is required !!!")
	private String type;	

	@NotBlank(message = "gym name is required !!!")
	private String gymName;
}
