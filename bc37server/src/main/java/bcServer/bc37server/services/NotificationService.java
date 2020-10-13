/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bcServer.bc37server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;

/**
 *
 * @author Laila
 */
@Service
public class NotificationService {
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;
    
    @Autowired

    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    public boolean sendNotification(String email){
        //kepada penerima
        SimpleMailMessage mail= new SimpleMailMessage();
mail.setTo(email);

mail.setFrom(sender);
mail.setSubject("From Nisa");
mail.setText("Hey! Welcome to our world!");

        try {
javaMailSender.send(mail);            
return true;
        } catch (MailException e) {
            System.out.println(e);
            return false;
        }
    }
}
