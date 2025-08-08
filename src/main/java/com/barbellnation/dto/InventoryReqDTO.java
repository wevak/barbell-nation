package com.barbellnation.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InventoryReqDTO {

    @NotBlank(message = "Name must not be blank")
    @Size(min = 2, max = 100, message = "Name must be between 2 to 100 characters")
    private String name;

    @Min(value = 1, message = "Price must be at least 1")
    private long price;

    @NotBlank(message = "Status must not be blank")
    private String status;

    @Min(value = 1, message = "Quantity must be at least 1")
    private long quantity;

    @NotNull(message = "Owner ID is required")
    private Long ownerId;
}
