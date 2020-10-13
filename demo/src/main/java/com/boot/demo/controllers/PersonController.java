/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boot.demo.controllers;

import com.boot.demo.entities.Person;
import com.boot.demo.services.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author ZFH
 */
@Controller
public class PersonController {
    PersonService service;
    
    @ResponseBody
    @RequestMapping(value = "data",method = RequestMethod.GET)
    public String printData(@RequestParam("key") String key){
        return service.searchPerson(key).toString();
    }
    
    @ResponseBody
    @GetMapping("dummy")
    public String dataDummy(){
//        service.saveAll();
        try {
            Person person = new Person("P01", "test", 20, "MALE", "test@email.com");
            service.save(person);
            System.out.println("method2");
             return "Berhasil save data dummy2";
        } catch (RuntimeException e) {
            return "Exception: "+e.getMessage();
        }
    }
}
