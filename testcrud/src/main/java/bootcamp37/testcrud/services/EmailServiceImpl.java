/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp37.testcrud.services;

import bootcamp37.testcrud.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author User
 */
@Service
public class EmailServiceImpl  {
    
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String fromemail;
    
    @Autowired
    private JavaMailSender emailSender;
    
//    @Transactional
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
