package com.barbellnation.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.barbellnation.custom_exceptions.ResourceNotFoundException;
import com.barbellnation.dto.PaymentReqDTO;
import com.barbellnation.dto.PaymentRespDTO;
import com.barbellnation.entities.CustomerPayment;
import com.barbellnation.service.CustomerPaymentService;

import jakarta.validation.Valid;

@RestController
//@Controller
@RequestMapping("/payments")
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerPaymentController {

    @Autowired
    private CustomerPaymentService service;

    @GetMapping("/list")
    public ResponseEntity<?> getAllPaymentList() {
        List<PaymentRespDTO> payments = service.getAllPayments();
        if (payments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/")
    public String init() {
        return "index";
    }

    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity<?> createPayment(@Valid @RequestBody PaymentReqDTO Cpayment
                                           ) throws Exception {
//        if (result.hasErrors()) {
//            Map<String, String> errors = result.getFieldErrors().stream()
//                .collect(Collectors.toMap(
//                    err -> err.getField(),
//                    err -> err.getDefaultMessage()
//                ));
//            return ResponseEntity.badRequest().body(errors);
//        }

        CustomerPayment createdPayment = service.createCustomerPayment(Cpayment);
        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
    }

    @PostMapping("/handle-payment-callback")
    public String handlePaymentCallback(@RequestParam Map<String, String> respPayLoad) {
        service.updatePayment(respPayLoad);
        return "success";
    }
}