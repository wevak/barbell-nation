package com.barbellnation.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

@Entity
@Table(name = "owner")
public class Owner extends BaseEntity implements UserDetails {
	@Column(name = "type", length = 50)
	private String type;

	@Column(name = "password", length = 60)
	private String password;

	@Column(name = "gym_name", length = 60)
	private String gymName;

	@OneToMany(mappedBy = "ownerId", orphanRemoval = true, cascade = CascadeType.ALL/* ,fetch = FetchType.EAGER */)
	private List<Trainer> trainers = new ArrayList<>();

	@OneToMany(mappedBy = "ownerId", orphanRemoval = true, cascade = CascadeType.ALL/* ,fetch = FetchType.EAGER */)
	private List<Package> packages = new ArrayList<>();

	@OneToMany(mappedBy = "ownerId", orphanRemoval = true, cascade = CascadeType.ALL/* ,fetch = FetchType.EAGER */)
	private List<Inventory> inventories = new ArrayList<>();

//	public Owner() {
//		super();
//		System.out.println(trainers);
//	}
	
	public void addPackage(Package pkg) {
		this.packages.add(pkg);
		pkg.setOwnerId(this);
	}
	
	//Helper method to de link a bi dir asso between Owner 1----* packages
		public void removePackage(Package pkg) {
			this.packages.remove(pkg);
			pkg.setOwnerId(null);
//			
		}

	// implement UserDetial i/f methods
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}// rets immutable List of roles - single role

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getEmail();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGymName() {
		return gymName;
	}

	public void setGymName(String gymName) {
		this.gymName = gymName;
	}

	public List<Package> getPackages() {
		return packages;
	}

	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}
	
	
}
