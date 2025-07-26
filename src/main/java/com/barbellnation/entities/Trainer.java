package com.barbellnation.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="trainer")
public class Trainer extends BaseEntity {

	@Column(name="years_of_experience", length = 50)
	private int yearsOfExperience;
	
	@ManyToOne
	@JoinColumn(name = "owner_id", nullable=false)
	private Owner ownerId;
	
	public int getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
}
