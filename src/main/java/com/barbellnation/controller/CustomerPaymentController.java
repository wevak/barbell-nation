package com.barbellnation.controller;

import com.barbellnation.dto.CreateOrderRequestDTO;
import com.barbellnation.dto.OrderResponseDTO;
import com.barbellnation.dto.PaymentReqDTO;
import com.barbellnation.dto.PaymentResponseDTO;
import com.barbellnation.service.CustomerPaymentService;
import com.razorpay.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payments")
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerPaymentController {

    @Autowired
    private CustomerPaymentService service;

    @Value("${razorpay.key.id}")
    private String razorpayKeyId;

    @PostMapping("/create-order")
    public ResponseEntity<OrderResponseDTO> createPaymentOrder(@RequestBody CreateOrderRequestDTO request) throws Exception {
        Order order = service.createRazorpayOrder(request.getPackageId());
        
        OrderResponseDTO response = new OrderResponseDTO();
        response.setKey(razorpayKeyId);
        response.setOrderId(order.get("id"));
        response.setAmount(order.get("amount"));
        response.setCurrency(order.get("currency"));
        
        return ResponseEntity.ok(response);
    }

    // This endpoint is now called AFTER a successful payment to register the user.
    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity<PaymentResponseDTO> registerCustomerAndCreatePayment(@Valid @RequestBody PaymentReqDTO paymentReqDTO) throws Exception {
        PaymentResponseDTO responseDTO = service.registerCustomerAndCreatePayment(paymentReqDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
//package com.barbellnation.controller;
//
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import com.barbellnation.custom_exceptions.ResourceNotFoundException;
//import com.barbellnation.dto.PaymentReqDTO;
//import com.barbellnation.dto.PaymentRespDTO;
//import com.barbellnation.entities.CustomerPayment;
//import com.barbellnation.service.CustomerPaymentService;
//
//import jakarta.validation.Valid;
//
//@RestController
////@Controller
//@RequestMapping("/payments")
//@CrossOrigin(origins = "http://localhost:5173")
//public class CustomerPaymentController {
//
//    @Autowired
//    private CustomerPaymentService service;
//
//    @GetMapping("/list")
//    public ResponseEntity<?> getAllPaymentList() {
//        List<PaymentRespDTO> payments = service.getAllPayments();
//        if (payments.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        }
//        return ResponseEntity.ok(payments);
//    }
//
//    @GetMapping("/")
//    public String init() {
//        return "index";
//    }
//
//    @PostMapping(value = "/create-order", produces = "application/json")
//    public ResponseEntity<?> createPayment(@Valid @RequestBody PaymentReqDTO Cpayment
//                                           ) throws Exception {
////        if (result.hasErrors()) {
////            Map<String, String> errors = result.getFieldErrors().stream()
////                .collect(Collectors.toMap(
////                    err -> err.getField(),
////                    err -> err.getDefaultMessage()
////                ));
////            return ResponseEntity.badRequest().body(errors);
////        }
//
//        CustomerPayment createdPayment = service.createCustomerPayment(Cpayment);
//        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
//    }
//
//    @PostMapping("/handle-payment-callback")
//    public String handlePaymentCallback(@RequestParam Map<String, String> respPayLoad) {
//        service.updatePayment(respPayLoad);
//        return "success";
//    }
//}