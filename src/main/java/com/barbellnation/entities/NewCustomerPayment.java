package com.barbellnation.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "new_customer_payment") // The table name in the database remains the same
@Getter
@Setter
public class NewCustomerPayment { // Class name has been changed

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name = "date_of_joining", nullable = false)
    private LocalDate dateOfJoining;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "razorpay_order_id")
    private String razorpayOrderId;

    @Column(name = "razorpay_payment_id")
    private String razorpayPaymentId;

    @Column(name = "razorpay_signature")
    private String razorpaySignature;

    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // Default constructor
    public NewCustomerPayment() {
        this.dateOfJoining = LocalDate.now();
        this.paymentStatus = "PENDING"; // Default status
    }
}
