package com.ttt.training.register.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ttt.training.register.entity.FormData;
import com.ttt.training.register.service.EmailService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class RegisterEmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping("/sendEmailWithAttachment")
	public ResponseEntity<String> sendEmailWithAttachment(@RequestBody FormData formData) {
		try {

			emailService.sendEmailWithAttachment(formData);
			return ResponseEntity.ok("Email with action buttons sent successfully!");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error sending email: " + e.getMessage());
		}
	}

	@GetMapping("/approve")
	public ResponseEntity<String> approveStudent(@RequestParam String email) {
		try {
			
			emailService.sendApprovalOrRejectionEmail(email, true);
			return ResponseEntity.ok("Approval email sent successfully!");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error approving student: " + e.getMessage());
		}
	}

	@GetMapping("/reject")
	public ResponseEntity<String> rejectStudent(@RequestParam String email) {
		try {
			
			emailService.sendApprovalOrRejectionEmail(email, false);
			return ResponseEntity.ok("Rejection email sent successfully!");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error rejecting student: " + e.getMessage());
		}
	}
}
