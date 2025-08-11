package com.barbellnation.dto;

// Structures the data sent to the frontend after creating a Razorpay order.
public class OrderResponseDTO {
    private String key;
    private String orderId;
    private Integer amount;
    private String currency;

    // Getters and Setters
    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public Integer getAmount() { return amount; }
    public void setAmount(Integer amount) { this.amount = amount; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
}
