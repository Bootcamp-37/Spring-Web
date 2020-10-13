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
    private String sender;
    
    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    
//    @Transactional
    public void sendingEmail(Person person, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(person.getEmail()); 
        message.setSubject(subject); 
        message.setText(text);
        javaMailSender.send(message);
    }
    
    
}
