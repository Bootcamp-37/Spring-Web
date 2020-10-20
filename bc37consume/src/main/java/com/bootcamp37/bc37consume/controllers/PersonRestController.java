/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp37.bc37consume.controllers;

import com.bootcamp37.bc37consume.entities.Person;
import com.bootcamp37.bc37consume.services.PersonRestService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    
    @GetMapping("/login")
    public String loginHalaman(){
        return "login";
    }
    
    @GetMapping("/user/employee")
    public String employee(){
        return "employee";
    }
    
    @GetMapping("/user/trainer")
    public String trainer(){
        return "trainer";
    }
    
    @GetMapping("/user/admin")
    public String admin(){
        return "admin";
    }
    

//    @GetMapping("")
    @RequestMapping("")
    public String getAll(Model model){
        model.addAttribute("people", service.getAll());
        model.addAttribute("person", new Person());
        return "tables";
    }
    

    @GetMapping("/refreshData")
    public ModelAndView refreshData(Model model){
        List<Person> persons = service.getAll();
        ModelAndView mv = new ModelAndView("index::table-person");
        mv.addObject("people",persons);
        return mv;
    }
    
    @ResponseBody
    @GetMapping("/{id}")
    public Person getById(String id){
//        System.out.println(id);
        Person p = service.getById(id);
        return p;
    }
    
    @ResponseBody
    @PostMapping("/save")
    public Map<String, String> save(Person person){
        Map<String, String> status = new HashMap<>();
        String hasil = service.save(person);
        status.put("status", hasil);
        return status;
    }
    
    @ResponseBody
    @GetMapping("/delete")
    public Map<String, String> delete(String id){
        Map<String, String> status = new HashMap<>();
        System.out.println(id);
        service.delete(id);
        status.put("status", "200");
        return status;
    }
    
    @GetMapping("/sb2admin")
    public String test(){
        return "sb2Admin/index.html";
    }
    

}
