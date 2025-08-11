//package com.barbellnation.service;
//
//import java.util.List;
//import java.util.Map;
//
//import com.barbellnation.dto.PaymentReqDTO;
//import com.barbellnation.dto.PaymentRespDTO;
//import com.barbellnation.entities.CustomerPayment;
//
//public interface CustomerPaymentService {
//
//    CustomerPayment createCustomerPayment(PaymentReqDTO c) throws Exception;
//
//    CustomerPayment updatePayment(Map<String, String> responsePayLoad);
//
//    List<PaymentRespDTO> getAllPayments(); // ✅ This must be implemented
//}
package com.barbellnation.service;

import com.barbellnation.dto.PaymentReqDTO;
import com.barbellnation.dto.PaymentResponseDTO;
import com.razorpay.Order;

public interface CustomerPaymentService {
    Order createRazorpayOrder(Long packageId) throws Exception;
    PaymentResponseDTO registerCustomerAndCreatePayment(PaymentReqDTO paymentReqDTO) throws Exception;
}
