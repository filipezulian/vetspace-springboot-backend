package com.pin.vetspace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.pin.vetspace.model.Email;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    
    
    public EmailService(JavaMailSender mailSender) {
    	this.mailSender = mailSender;
    }

    /*public void sendEmail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("equipeVetspace@email.com");
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());
        mailSender.send(message);
    }*/
    
    public void sendEmail(Email email) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("equipeVetspace@email.com");
            message.setTo(email.getTo());
            message.setSubject(email.getSubject());
            message.setText(email.getBody());
            mailSender.send(message);
        }
}
