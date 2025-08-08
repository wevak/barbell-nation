package com.barbellnation.dto;

import jakarta.validation.constraints.NotBlank;

public class PackageReqDTO {
	@NotBlank(message = "owner id is required")
	private Long id;
	
	@NotBlank(message = "package name is required")
	private String name;

	
	@NotBlank(message = "package duration is required")
	private String duration;
	
	@NotBlank(message = "package amount is required")
	private String amount;
	
	

	@Override
	public String toString() {
		return "PackageReqDTO [ownerId=" + id + ", name=" + name + ", duration=" + duration + ", amount=" + amount
				+ "]";
	}

	public Long getOwnerId() {
		return id;
	}

	public void setOwnerId(Long ownerId) {
		this.id = ownerId;
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
