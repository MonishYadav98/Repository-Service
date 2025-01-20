package com.ttt.training.register.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ttt.training.register.entity.FormData;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendEmailWithAttachment(FormData formData) throws MessagingException {

		String emailBody = "<p>Hello,</p>" + "<p>Please review the application for the following student:</p>"
				+ "<p>Name: " + formData.getFullName() + "</p>" + "<p>Phone: " + formData.getPhoneNumber() + "</p>"
				+ "<p>Email: " + formData.getEmail() + "</p>" + "<p>Address: " + formData.getAddress() + "</p>"
				+ "<p>Graduation Stream: " + formData.getUniversityStream() + "</p>" + "<p>Course Wants to enroll in : "
				+ formData.getCourseDetails() + "</p>" + "<p>Year of Passing : " + formData.getYear() + "</p>"
				+ "<p>CGPA: " + formData.getCgpa() + "</p>"
				+ "<p>Click below to either accept or reject the application:</p>"
				+ "<table style='border: 0; padding: 0; margin: 0;'>" + "<tr>" + "<td style='padding-right: 10px;'>"
				+ "<a href='http://localhost:8080/api/approve?email=" + formData.getEmail()
				+ "' style='background-color: #4CAF50; color: white; padding: 10px 20px; text-align: center; text-decoration: none; border-radius: 5px;'>Accept</a>"
				+ "</td>" + "<td>" + "<a href='http://localhost:8080/api/reject?email=" + formData.getEmail()
				+ "' style='background-color: #f44336; color: white; padding: 10px 20px; text-align: center; text-decoration: none; border-radius: 5px;'>Reject</a>"
				+ "</td>" + "</tr>" + "</table>";

//				+ "<a href='http://localhost:8080/api/approve?email=" + formData.getEmail() + "'>Accept</a><br>"
//				+ "<a href='http://localhost:8080/api/reject?email=" + formData.getEmail() + "'>Reject</a>";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo("monishyadav2798@gmail.com");
		helper.setSubject(formData.getFullName() + " Enrollment Data");
		helper.setText(emailBody, true);

		mailSender.send(message);
	}

	public void sendApprovalOrRejectionEmail(String studentEmail, boolean isApproved) throws MessagingException {

		String emailBody;
		String subject = isApproved ? "Approval Notification" : "Rejection Notification";

		if (isApproved) {
			emailBody = "<p>Congratulations! Your application has been approved.</p>";
		} else {
			emailBody = "<p>We regret to inform you that your application has been rejected.</p>";
		}

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, false);

		helper.setTo(studentEmail);
		helper.setSubject(subject);
		helper.setText(emailBody, true);

		mailSender.send(message);
	}
}
