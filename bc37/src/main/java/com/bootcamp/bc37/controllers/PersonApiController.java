/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.bc37.controllers;

import com.bootcamp.bc37.entities.Person;
import com.bootcamp.bc37.services.PersonService;
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
 * @author aqira
 */
//localhost:8085/api/
@RequestMapping("api")
@RestController
public class PersonApiController {

    PersonService service;

    @Autowired
    public PersonApiController(PersonService service) {
        this.service = service;
    }

    //Representational State Transfer-ful
    // getall/
    @GetMapping("")
    public List<Person> getAll() {
        service.saveAll();
        return service.getAll();
    }

    // /getbyid/1
    @GetMapping("{id}")
    public Person getById(@PathVariable String id) {
        return service.getById(id).get();
    }

    // /insert/
    @PostMapping("")
    public ResponseEntity<String> insert(@RequestBody Person person) {
        String result = service.save(person);
        //berhasil
        if (result.equals("success")) {
            return ResponseEntity
                    .ok()
                    .body("Insert Berhasil!");
        } 
        //sudah ada
        else if (result.equals("cannot update")) {
            return ResponseEntity
                    .status(409)
                    .body("Tidak dapat update, silahkan gunakan method lain!");
        }
        //error
        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body("Gagal memasukkan!");
    }

    //ubah jadi response entity seperti method insert ^
    // /update/1
    @PutMapping("")
    public boolean update(@RequestBody Person person) {
        return service.update(person);
    }

    // /delete/1
    @DeleteMapping("{id}")
    public boolean delete(@PathVariable String id) {
        return service.delete(id);
    }
}
