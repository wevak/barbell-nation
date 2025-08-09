package com.barbellnation.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageDao extends JpaRepository<com.barbellnation.entities.Package, Long> {
	Optional<com.barbellnation.entities.Package> findByName(String packageName);
	
}
