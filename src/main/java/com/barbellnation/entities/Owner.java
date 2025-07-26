package com.barbellnation.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="owner")
public class Owner extends BaseEntity {
	@Column(name="type", length = 50)
	private String type;
	
	@Column(name="password", length = 60)
	private String password;
	
	@Column(name="gym_name", length = 60)
	private String gymName;
	
	@OneToMany(mappedBy = "ownerId", orphanRemoval = true,
			cascade = CascadeType.ALL/* ,fetch = FetchType.EAGER */)
	private List<Trainer> trainers = new ArrayList<>();
	
	@OneToMany(mappedBy = "ownerId", orphanRemoval = true,
			cascade = CascadeType.ALL/* ,fetch = FetchType.EAGER */)
	private List<Package> packages = new ArrayList<>();
	
	@OneToMany(mappedBy = "ownerId", orphanRemoval = true,
			cascade = CascadeType.ALL/* ,fetch = FetchType.EAGER */)
	private List<Inventory> inventories = new ArrayList<>();
	
//	public Owner() {
//		super();
//		System.out.println(trainers);
//	}
}
