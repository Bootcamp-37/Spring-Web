/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp37.testcrud.controllers;

import bootcamp37.testcrud.entities.Person;
import bootcamp37.testcrud.services.EmailServiceImpl;
import bootcamp37.testcrud.services.PersonService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author User
 */
@Controller
public class PersonController {
    
    PersonService service;
    EmailServiceImpl emailService;

    @Autowired
    public PersonController(PersonService service, EmailServiceImpl emailService) {
        this.service = service;
        this.emailService = emailService;
    }
    
//    @ResponseBody
//    @GetMapping("")
//    public void index(){
////        return service.getAll().toString();
////        return "index";
//    }
    
    @ResponseBody
    @GetMapping("dummy")
    public String dataDummy(){
//        service.saveAll();
        try {
            Person person = new Person("P01", "test", "test@email.com", "MALE", 0);
            service.save(person);
            System.out.println("method2");
             return "Berhasil save data dummy2";
        } catch (RuntimeException e) {
            return "Exception: "+e.getMessage();
        }
        
       
    }
    
    @ResponseBody
    @RequestMapping(value = "data",method = RequestMethod.GET)
    public String printData(@RequestParam("key") String key){
        return service.searchPerson(key).toString();
    }
    
    @ResponseBody
    @GetMapping("email")
    public String sendEmail(){
        try {
            Person person = service.getById("P001").get();
            emailService.sendingEmail(person, "From Muhammad Fikri Ridhwan", "Send email Test from Fikri");
            return "berhasil "+person.getName();
        } catch (Exception e) {
            e.printStackTrace();
            return "gagal";
        }
    }
    
    

    
    
}
