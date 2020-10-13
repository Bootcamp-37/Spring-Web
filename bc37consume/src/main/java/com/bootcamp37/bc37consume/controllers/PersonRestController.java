/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp37.bc37consume.controllers;

import com.bootcamp37.bc37consume.entities.Person;
import com.bootcamp37.bc37consume.services.PersonRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Laila
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
        model.addAttribute("people",service.getAll());
        model.addAttribute("person",new Person());
        return "index1";
    }
    
    @PostMapping("/save")
    
    public String save(Person person){
        service.save(person);
        return "redirect:/";
    }
    
    
}
