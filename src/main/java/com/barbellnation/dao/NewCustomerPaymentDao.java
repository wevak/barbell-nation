package com.barbellnation.dao;

import com.barbellnation.entities.NewCustomerPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This repository interface handles database operations for the NewCustomerPayment entity.
@Repository
public interface NewCustomerPaymentDao extends JpaRepository<NewCustomerPayment, Long> {
}