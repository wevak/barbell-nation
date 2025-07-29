package com.barbellnation.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barbellnation.entities.Owner;

public interface OwnerDao extends JpaRepository<Owner, Long> {
	Optional<Owner> findByEmail(String email);
}
