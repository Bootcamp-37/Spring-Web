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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    public String getAll(Model model) {
        model.addAttribute("people", service.getAll());
        model.addAttribute("person", new Person());
        return "index1";
    }

    @GetMapping("/login")
    public String login() {
        return "login1";
    }

    @GetMapping("/tables")
    public String tables() {
//        model.addAttribute("people",service.getAll());
//        model.addAttribute("person",new Person());
        return "tables";
    }

    @GetMapping("/refreshData")
    public ModelAndView refreshData(Model model) {
        List<Person> persons = service.getAll();
        ModelAndView mv = new ModelAndView("index1::table-person");
        mv.addObject("people", persons);
        return mv;
    }
//    @GetMapping("/refreshData")
//    public ModelAndView showPage(@PathVariable("id") String id,@PathVariable("name") String name, 
//            @PathVariable("email") String email,@PathVariable("gender") String gender, @PathVariable("age") Integer age, HttpServletResponse response) {  
////List<Person> personList= new ArrayList<>();
//        ModelAndView mv= new ModelAndView("index1::table-person"); 
//        Person person = new Person(id, name, email, gender, age);
//       return new ModelAndView("index1", "person", person);
//    } 

    @PostMapping("/save")
    public Map<String, String> save(Person person) {
        Map<String, String> status = new HashMap<>();
        String hasil = service.save(person);
        status.put("status", hasil);
        return status;
    }

    @ResponseBody
    @GetMapping("delete")
    public Map<String, String> delete(String id) {
        Map<String, String> param = new HashMap<>();
        service.delete(id);
        param.put("result", "berhasil");
        return param;

    }

    @GetMapping("getById")
    @ResponseBody
    public Person getById(String id) {
        return service.getById(id);
    }
}
