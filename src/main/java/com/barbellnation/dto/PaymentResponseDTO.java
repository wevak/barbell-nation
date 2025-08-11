package com.barbellnation.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

// A safe object to return to the frontend after successful registration.
// Avoids lazy loading exceptions.
public class PaymentResponseDTO {
    private Long paymentId;
    private BigDecimal amount;
    private LocalDate dateOfJoining;
    private String paymentStatus;
    private String customerName;
    private String packageName;

    // Constructors, Getters, and Setters
    public PaymentResponseDTO(Long paymentId, BigDecimal amount, LocalDate dateOfJoining, String paymentStatus, String customerName, String packageName) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.dateOfJoining = dateOfJoining;
        this.paymentStatus = paymentStatus;
        this.customerName = customerName;
        this.packageName = packageName;
    }

    // Getters and Setters
    public Long getPaymentId() { return paymentId; }
    public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public LocalDate getDateOfJoining() { return dateOfJoining; }
    public void setDateOfJoining(LocalDate dateOfJoining) { this.dateOfJoining = dateOfJoining; }
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getPackageName() { return packageName; }
    public void setPackageName(String packageName) { this.packageName = packageName; }
}
