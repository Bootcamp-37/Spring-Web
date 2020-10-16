/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp37.testcrud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author Deo Lahara
 */

@Service
public class SendEmailServices {
    @Value("${spring.mail.username}")
    String fromemail;
    
    @Autowired
    private JavaMailSender emailSender;
 
    public void sendSimpleMessage(
      String to, String subject, String text) {
        
        
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("fromemail");
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        emailSender.send(message);
        
    }

    
    
}
