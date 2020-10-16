/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp37.bc37consume.controllers;


import com.bootcamp37.bc37consume.entities.Person;
import com.bootcamp37.bc37consume.services.PersonRestService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import static org.springframework.web.servlet.function.RequestPredicates.param;

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
        model.addAttribute("person", new Person());
        return "index";
    }
    
    @ResponseBody
    @GetMapping("getById")
    public Person getById (String id){
        return service.getById(id);
    }
    
    @PostMapping("/save")
    public String save (Person person){
        service.save(person);
        return "redirect:/";
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
    
}

