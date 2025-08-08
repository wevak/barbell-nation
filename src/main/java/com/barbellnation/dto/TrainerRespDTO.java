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
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TrainerRespDTO {
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String gender;
	private int yearsOfExperience;	
	
	private Long packageId;
    

}
