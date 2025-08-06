package com.barbellnation.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barbellnation.entities.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {
	  Optional<Customer> findById(Long id);
}
