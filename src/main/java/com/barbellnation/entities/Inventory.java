package com.barbellnation.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Entity
@Table(name = "inventory")
public class Inventory {
	@Id
	@Column(name = "inventory_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long inventoryId;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private long price;
	
	@Column(nullable = false)
	private String status;
	
	@Column(nullable = false)
	private long quantity;
	
	@ManyToOne
	@JoinColumn(name = "owner_id", nullable=false)
	private Owner ownerId;
}
