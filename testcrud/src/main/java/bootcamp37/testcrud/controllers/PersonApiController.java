/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp37.testcrud.controllers;

import bootcamp37.testcrud.entities.Person;
import bootcamp37.testcrud.services.PersonService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author User
 */
@RequestMapping("api")
@RestController
public class PersonApiController {

    PersonService service;


    @Autowired
    public PersonApiController(PersonService service) {
        this.service = service;
    }



    @GetMapping("")
    public List<Person> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public Person getById(@PathVariable String id) {
        return service.getById(id).get();
    }

    @PostMapping("")
    public Person insert(@RequestBody Person person) {
        Person result = service.save(person);
        
//        if (result.equals("insert")) {
//            return ResponseEntity.ok().body("insert");
//        } else if (result.equals("update")) {
//            return ResponseEntity.ok().body("update");
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Gagal dimasukkan");
//        }
        return result;
    }
    
    @PutMapping("")
    public boolean update(@RequestBody Person person){
        return service.updatePerson(person);
    }
    
    @DeleteMapping("{id}")
    public Map<String,String> delete(@PathVariable String id){
        boolean isSuccess = service.delete(id);
        Map<String, String> status = new HashMap<>();
        if (isSuccess) {
            status.put("status", "200");
            return status;
        }else{
            return status;
        }
    }
    

}
