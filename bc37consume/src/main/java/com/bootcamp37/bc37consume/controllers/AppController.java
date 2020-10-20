/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp37.bc37consume.controllers;

import com.bootcamp37.bc37consume.entities.UserApp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author User
 */
@RestController
public class AppController {
    
    List<UserApp> users = new ArrayList<>();
    
    public void setUser(){
        users.add(new UserApp(1, "fikri"));
        users.add(new UserApp(2, "iqwal"));
        users.add(new UserApp(3, "zakky"));
        users.add(new UserApp(4, "nisa"));        
    }
    
    @GetMapping("/employee")
    public String getKaryawan(){
        return "karyawan";
    }
    
    @GetMapping("/trainer")
    public String getTrainer(){
        return "trainer";
    }
    
    @GetMapping("/admin")
    public String getAdmin(){
        return "admin";
    }
    
}
