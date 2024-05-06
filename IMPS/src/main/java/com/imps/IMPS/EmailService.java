package com.imps.IMPS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
	private String sender;
    
    public EmailService(JavaMailSender mailSender) {
    	this.mailSender = mailSender;
    }
    
    @Async
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(sender);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
    
    @Async
    public void sendAccepted(String to, String requestID) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(sender);
        message.setSubject("Print Request #" + requestID + " has been approved!");
        message.setText("Print request #" + requestID + " has been approved. Please wait while your document is being processed.\n\nThis is an auto-generated email. Please do not reply.\n\nThanks,\nIMPS");

        mailSender.send(message);
    }
    
    @Async
    public void sendRejected(String to, String requestID) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(sender);
        message.setSubject("Print Request #" + requestID + " has been rejected");
        message.setText("Print request #" + requestID + " has been rejected. Please go to 'Printing Records' for Request #" + requestID + " and check comments.\n\nThis is an auto-generated email. Please do not reply.\n\nThanks,\nIMPS");

        mailSender.send(message);
    }
}