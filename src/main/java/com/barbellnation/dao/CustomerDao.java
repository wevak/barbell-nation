package com.barbellnation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barbellnation.entities.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

}
