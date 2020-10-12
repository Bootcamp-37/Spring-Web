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
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author aqira
 */
//@RestController
@Controller
public class PersonRestController {

    PersonRestService service;

    @Autowired
    public PersonRestController(PersonRestService service) {
        this.service = service;
    }

    //menampilkan json
    @GetMapping("perbandingan")
    @ResponseBody
    public List<Person> getAll() {
        return service.getAll();
    }

    //dipake ke index.html memakai model
    @GetMapping("")
    public String getAll(Model model) {
        //thymeleaf
        model.addAttribute("people", service.getAll());
        return "index";
    }
    
    @GetMapping("/trainer")
    public String halamanTrainer(){
        return "trainer";
    }
    @GetMapping("/admin")
    public String halamanAdmin(){
        return "admin";
    }
    @GetMapping("/karyawan")
    public String halamanKaryawan(){
        return "karyawan";
    }
}
