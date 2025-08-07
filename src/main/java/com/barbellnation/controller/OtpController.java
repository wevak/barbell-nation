package com.barbellnation.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbellnation.dto.SendOtpRequestDto;
import com.barbellnation.dto.VerifyOtpRequestDto;
import com.barbellnation.service.OtpService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/public")
@CrossOrigin(origins = "http://localhost:5173")
public class OtpController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody @Valid SendOtpRequestDto request) {
    	System.out.println("in sendOtp");
        otpService.sendOtp(request.getEmail());
        return ResponseEntity.ok("OTP sent to email");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody @Valid VerifyOtpRequestDto request) {
        boolean verified = otpService.verifyOtp(request.getEmail(), request.getOtp());
        if (verified) {
            return ResponseEntity.ok("OTP verified");
        } else {
            return ResponseEntity.status(400).body("Invalid or expired OTP");
        }
    }
}
