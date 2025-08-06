package com.barbellnation.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.barbellnation.entities.CustomerPayment;

public interface PaymentDao extends JpaRepository<CustomerPayment, Long> {
    Optional<CustomerPayment> findByRazorpayPaymentId(String paymentId);
}