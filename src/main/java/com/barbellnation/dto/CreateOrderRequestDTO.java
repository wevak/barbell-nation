package com.barbellnation.dto;

// Used to receive the packageId from the frontend to create a payment order.
public class CreateOrderRequestDTO {
    private Long packageId;

    // Getters and Setters
    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }
}
