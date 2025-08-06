package com.barbellnation.entities;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer_payment")
@Setter
@Getter
@ToString(exclude = {"customer"})
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private long id;

    @CreationTimestamp
    @Column(name = "date_of_joining")
    private LocalDate dateOfJoining;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private String paymentStatus;

    private String razorpayPaymentId;
}