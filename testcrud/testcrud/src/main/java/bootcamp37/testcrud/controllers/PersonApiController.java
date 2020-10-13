/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp37.testcrud.controllers;

import bootcamp37.testcrud.entities.Person;
import bootcamp37.testcrud.services.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        service.saveAll();
        return service.getAll();
    }

    @GetMapping("{id}")
    public Person getById(@PathVariable String id) {
        return service.getById(id).get();
    }

    @PostMapping("")
    public ResponseEntity<String> insert(@RequestBody Person person) {
        String result = service.save(person);
        if (result.equals("success")) {
            return ResponseEntity.ok().body("Insert Berhasil");
        } else if (result.equals("cannot update")) {
            return ResponseEntity.status(409).body("Tidak dapat update. data sudah tersedia");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Gagal dimasukkan");
        }
    }
    
    @PutMapping("")
    public boolean update(@RequestBody Person person){
        return service.updatePerson(person);
    }
    
    @DeleteMapping("{id}")
    public boolean delete(@PathVariable String id){
        return service.delete(id);
    }
    

}
