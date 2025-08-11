//package com.barbellnation.dto;
//
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotNull;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//@Setter
//@Getter
//@ToString
//@AllArgsConstructor
//@NoArgsConstructor
//public class PaymentReqDTO {
//
//    @NotNull(message = "Customer ID is required")
//    private Long cid;
//
//    @Min(value = 1, message = "Amount must be greater than zero")
//    private int amount;
//    
//    private String currency;
//
////	public Long getCustomerId() {
////		// TODO Auto-generated method stub
////		return null;
////	}
//}
package com.barbellnation.dto;

import lombok.Getter;
import lombok.Setter;

// Receives all customer and payment details after a successful transaction.
@Getter
@Setter
public class PaymentReqDTO {
    // Customer Details
    private String name;
    private String email;
    private String gender;
    private String phone;
    private Long packageId;

    // Razorpay Verification Details
    private String razorpay_payment_id;
    private String razorpay_order_id;
    private String razorpay_signature;
}
