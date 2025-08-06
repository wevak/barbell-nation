package com.barbellnation.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class OtpService {

	@Autowired
	private JavaMailSender mailSender;

	private final Map<String, OtpEntry> otpStore = new ConcurrentHashMap<>();

	private final int EXPIRY = 5;

	public void sendOtp(String email) {
		String otp = generateOtp();
		otpStore.put(email, new OtpEntry(otp, LocalDateTime.now()));

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("OTP Code to complete the registration in The Vanity and the Broom");
		message.setText("Your OTP is: " + otp + "\nIt is valid for 5 minutes.");

		mailSender.send(message);
	}

	public boolean verifyOtp(String email, String otp) {
		if (!otpStore.containsKey(email))
			return false;

		OtpEntry entry = otpStore.get(email);
		if (entry.getOtp().equals(otp)) {
			if (entry.getCreatedAt().plusMinutes(EXPIRY).isAfter(LocalDateTime.now())) {
				otpStore.remove(email); // invalidate after use
				return true;
			}
		}

		return false;
	}

	private String generateOtp() {
		int otp = 100000 + (int) (Math.random() * 900000); // 6-digit
		return String.valueOf(otp);
	}

	private static class OtpEntry {
		private final String otp;
		private final LocalDateTime createdAt;

		public OtpEntry(String otp, LocalDateTime createdAt) {
			this.otp = otp;
			this.createdAt = createdAt;
		}

		public String getOtp() {
			return otp;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}
	}
}
