/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boot.demo.services;

import com.boot.demo.entities.Person;
import com.boot.demo.repositories.PersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.TransactionScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ZFH
 */
@Service
public class PersonService {
    PersonRepository personRepository;
     
    @Autowired
    public PersonService(PersonRepository repo) {
        this.personRepository = repo;
    }
    
     public List<Person> getAll(){
        return personRepository.findAll();
    }
    
    public Optional<Person> getById(String id){
        Optional<Person> person = personRepository.findById(id);
        return person;
    }
    
    public boolean updatePerson(Person person){
        Optional<Person> personOptional = getById(person.getId());
        if (personOptional.isPresent()) {
            Person update = personOptional.get();
            update.setName(person.getName());
            update.setEmail(person.getEmail());
            update.setAge(person.getAge());
            update.setGender(person.getGender());
            personRepository.save(update);
            return true;
        }
        return false;
    }
    
     @TransactionScoped
    public String save(Person person){
        boolean personCheck = getById(person.getId()).isPresent();
        if (personCheck) {
            return "cannot update";
        }
        try {
            personRepository.save(person);
            return "success";
        } catch (Exception e) {
            System.out.println(e);
            return "error";
        }
    }
    
     public String searchPerson(String key){
        String json = "";
        try {
            List<Person> persons = personRepository.findByNameContainingOrGenderContainingOrEmailContainingOrIdContaining(key,key,key,key);
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(persons);

        } catch (JsonProcessingException ex) {
            Logger.getLogger(PersonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
     
     public boolean delete(String id){
        try {
            personRepository.delete(new Person(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
