/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.bc37.services;

import com.bootcamp.bc37.entities.Person;
import com.bootcamp.bc37.repositories.PersonRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author aqira
 */
@Service
public class PersonService {

    //Dependency Injection
    PersonRepository personRepository;
    NotificationService notificationService;

    @Autowired
    public PersonService(PersonRepository personRepository, NotificationService notificationService) {
        this.personRepository = personRepository;
        this.notificationService = notificationService;
    }

    //CRUD + getbyid
    //GetAll
    public List<Person> getAll() {
        List<Person> people = personRepository.findAll();
        return people;
    }

    //GetById
    public Optional<Person> getById(String id) {
        Optional<Person> person = personRepository.findById(id);
        return person;
    }

    //Update
    public boolean update(Person person) {
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

    //Delete
    public boolean delete(String id) {

        boolean personCheck = getById(id).isPresent(); //Java Null Pointer
        if (personCheck) {
            personRepository.delete(getById(id).get());
            return true;
        }
        return false;
    }

    //Insert
    public String save(Person person) {
        boolean personCheck = getById(person.getId()).isPresent();
        if (personCheck) {
            return "cannot update";
        }
        try {
            personRepository.save(person); //transcational rollback
            notificationService.sendNotification(person.getEmail());
            return "success";
        } catch (Exception e) {
            System.out.println(e);
            return "error";
        }
    }

    public void saveAll() {
        List<Person> people = new ArrayList<>();
        people.add(new Person("P001", "Joko", "jokos@gmail.com", "MALE", 35));
        people.add(new Person("P002", "Aqira", "aqira@gmail.com", "MALE", 23));
        people.add(new Person("P003", "Fikri", "fikri@gmail.com", "MALE", 23));
        people.add(new Person("P004", "Iqwal", "iqwal@gmail.com", "MALE", 23));
        people.add(new Person("P005", "Zakky", "zakky@gmail.com", "MALE", 23));
        people.add(new Person("P006", "Nisa", "nisa@gmail.com", "FEMALE", 23));
        personRepository.saveAll(people);
    }
}
