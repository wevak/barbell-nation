package com.barbellnation.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="payment")
public class Payment {
    @Id
    @Column(name="payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name="date_of_joining", nullable = false)
    private LocalDate dateOfJoining;
    
    @Column(name="expiry_date", nullable = false)
    private LocalDate dateOfExpiry;
    
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customerId;
    
    
    
    
}
