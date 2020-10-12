/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.bc37.controllers;

import com.bootcamp.bc37.entities.Person;
import com.bootcamp.bc37.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author aqira
 */
@Controller
public class PersonController {

    PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("")
    public String index() {
        System.out.println(service.getAll());
        return "index";
    }

    @ResponseBody
    @GetMapping("update")
    public boolean update() {
        Person person = new Person();
        person.setId("P001");
        person.setAge(23);
        person.setGender("Male");
        return service.update(person);
    }

    @ResponseBody
    @GetMapping("insert")
    public String tesInsert() {
        Person person = new Person();
        person.setId("P001");
        person.setName("Aqira Kelana");
        person.setEmail("aqira.kelana@gmail.com");
        person.setAge(23);
//        person.setGender("Male");
        return service.save(person);
    }
    
}
