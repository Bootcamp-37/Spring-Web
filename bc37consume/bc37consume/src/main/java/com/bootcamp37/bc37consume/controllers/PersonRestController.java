/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp37.bc37consume.controllers;

import com.bootcamp37.bc37consume.entities.Person;
import com.bootcamp37.bc37consume.services.PersonRestService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author User
 */
@Controller
public class PersonRestController {
    PersonRestService service;

    @Autowired
    public PersonRestController(PersonRestService service) {
        this.service = service;
    }
    
    @GetMapping("")
    public String getAll(Model model){
        model.addAttribute("people", service.getAll());
        return "index";
    }
    
    @GetMapping("/{id}")
    public String getById(@PathVariable String id){
        Person p = service.getById(id);
        return p.toString();
    }
    
    @GetMapping("/register")
    public String register(Model model){
        Person person = new Person();
        model.addAttribute("person", person);
        return "register";
    }
}
