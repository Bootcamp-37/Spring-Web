/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.bc37.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author aqira
 */
@Service
public class NotificationService {

    private JavaMailSender javaMailSender;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.username}")
    private String sender;

    public boolean sendNotification(String email) {
        SimpleMailMessage mail = new SimpleMailMessage();

        //kepada penerima
        mail.setTo(email); //aqira.kelana@gmail.com

        //pengirimnya
        mail.setFrom(sender);

        //email subject 
        mail.setSubject("Hello [nama anda yang dikirim]");

        //email body
        mail.setText("Dear [Name]," + "\n"
                + "ini dari Fikri!");
        try {
            javaMailSender.send(mail);
            return true;
        } catch (MailException me) {
            System.out.println(me);
            return false;
        }
    }
}
