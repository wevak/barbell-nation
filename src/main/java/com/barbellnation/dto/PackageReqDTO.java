package com.barbellnation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class PackageReqDTO {
	@NotBlank(message = "owner id is required")
	private int ownerId;
	
	@NotBlank(message = "package name is required")
	private String name;

	
	@NotBlank(message = "package duration is required")
	private String duration;
	
	@NotBlank(message = "package amount is required")
	private String amount;
	
	

	@Override
	public String toString() {
		return "PackageReqDTO [ownerId=" + ownerId + ", name=" + name + ", duration=" + duration + ", amount=" + amount
				+ "]";
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
}
