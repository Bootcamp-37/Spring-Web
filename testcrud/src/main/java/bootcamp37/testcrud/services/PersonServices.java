/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp37.testcrud.services;

import bootcamp37.testcrud.entities.Person;
import bootcamp37.testcrud.repositories.PersonRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Deo Lahara
 */

@Service
public class PersonServices {
    PersonRepository personRepository;
    SendEmailServices mailservices;

    @Autowired
    public PersonServices(PersonRepository personRepository, SendEmailServices mailservices) {
        this.personRepository = personRepository;
        this.mailservices = mailservices;
    }

    //CRUD + getById
    
    //GetAll
    public List<Person> getAll(){
        List<Person> people = this.personRepository.findAll();
        return people;
    }
    
    //GetById
    public Optional<Person> getById(String id){
        Optional<Person> person = this.personRepository.findById(id);
        return person;
    }
    
    //SearchKeyword
    public List<Person> search (String keyword){
        List<Person> persons = (List<Person>) personRepository.search(keyword);
        return persons;
    }
        
    //Update
    public String update (Person person){
        Optional<Person> personOptional = getById(person.getId());
        if (personOptional.isPresent()){
            Person personUpdate = personOptional.get();
            personUpdate.setName(person.getName());
            personUpdate.setEmail(person.getEmail());
            personUpdate.setGender(person.getGender());
            personUpdate.setAge(person.getAge());
            this.personRepository.save(personUpdate);
            return "Updated";
        }
    return "Error";
    }
        
    //Delete
    public boolean delete(String id){
        try {
            Person p = getById(id).get();
            System.out.println(p.toString());
            personRepository.delete(p);
            System.out.println(getById(id).get().toString()+" ini test");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    //Insert atau Update
    public String save(Person person){
        boolean personCheck = getById(person.getId()).isPresent();
        System.out.println(personCheck);
        if (personCheck){
            System.out.println(update(person));
            return update(person);
        }
        try {
            this.personRepository.save(person);
            this.mailservices.sendSimpleMessage(person.getEmail(), "Iqwal Application", "Dear, "+person.getName()+" Thank you. !");
            System.out.println(personCheck);
            return "Success";
        } catch (Exception e) {
            System.out.println(e);
            return "Error";
        }
}
    
    public void saveAll(){
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("P001", "Iqwal", 23, "Male", "Iqwal@gmail.com"));
        persons.add(new Person("P002", "Fikri", 23, "Male", "fkry@gmail.com"));
        persons.add(new Person("P003", "Zaky", 23, "Male", "zaky@gmail.com"));
        persons.add(new Person("P004", "Aqira", 23, "Male", "aqira@gmail.com"));
        persons.add(new Person("P005", "Nisa", 23, "Female", "nisa@gmail.com"));
        this.personRepository.saveAll(persons);
        
        System.out.println(personRepository.saveAll(persons));
    }
}
