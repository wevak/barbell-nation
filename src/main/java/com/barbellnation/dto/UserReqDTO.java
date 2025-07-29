package com.barbellnation.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

public class UserReqDTO {
	@NotBlank(message = "first name is required !!!")
	private String name;
	
//	@NotBlank(message = "last name is required !!!")
//	@Length(min=3,max=15,message = "invalid no of chars in last name")
//	private String lastName;
	
	@Email(message = "invalid email format !")
	private String email;
	
	@NotBlank(message = "phone is required !!!")
	private String phone;
	
	@NotBlank(message = "gender is required !!!")
	private String gender;
	
	@NotBlank(message = "type is required !!!")
	private String type;
	
	@Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})",
			message = "Invalid password format !!!")
	private String password;
	
	@NotBlank(message = "gym name is required !!!")
	private String gymName;
	
	
//	@NotNull(message = "dob is required")
//	@Past
//	private LocalDate dob;
//	@NotNull
//	private UserRole role;
	

//	@Override
//	public String toString() {
//		return "UserReqDTO [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
//				+ password + ", dob=" + dob + "]";
//	}
	
	//Getters&Setters
//	public String getFirstName() {
//		return firstName;
//	}
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//	public String getLastName() {
//		return lastName;
//	}
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
	
	public String getEmail() {
		return email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGymName() {
		return gymName;
	}
	public void setGymName(String gymName) {
		this.gymName = gymName;
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
//	public LocalDate getDob() {
//		return dob;
//	}
//	public void setDob(LocalDate dob) {
//		this.dob = dob;
//	}
	
	
}
