package com.barbellnation.dto;

import jakarta.validation.constraints.NotBlank;
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
public class PaymentRespDTO {
//	@NotBlank(message = "payment id should not be null")
    private long id;
    
//	@NotBlank(message = "Enter the amount")
    private int amount;
    
}
