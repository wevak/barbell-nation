package com.barbellnation.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="package")
public class Package {
	@Id
	@Column(name="package_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long packageId;
	
	@Column(name="name", unique = true, nullable = false)
	private String name;
	
	@Column(name="duration", unique = true, nullable = false)
	private String duration;
	
	@Column(name="amount", unique = true, nullable = false)
	private String amount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id", nullable=false)
	private Owner ownerId;
	
	@OneToMany(mappedBy = "packageId", orphanRemoval = true,
			cascade = CascadeType.ALL/* ,fetch = FetchType.EAGER */)
	private List<Customer> customers = new ArrayList<>();

	public long getPackageId() {
		return packageId;
	}

	public void setPackageId(long packageId) {
		this.packageId = packageId;
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

//	public Long getOwnerId() {
//		return ownerId.getId();
//	}

	public void setOwnerId(Owner ownerId) {
		this.ownerId = ownerId;
	}

	public Owner getOwnerId() {
		return ownerId;
	}
	
}
