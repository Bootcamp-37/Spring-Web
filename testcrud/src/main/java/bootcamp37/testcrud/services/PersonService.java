/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp37.testcrud.services;

import bootcamp37.testcrud.Repositories.PersonRepository;
import bootcamp37.testcrud.entities.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class PersonService {
    
    PersonRepository personRepository;
    EmailServiceImpl mailservices;

    @Autowired
    public PersonService(PersonRepository repo, EmailServiceImpl mailservices ) {
        this.personRepository = repo;
        this.mailservices= mailservices;
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
    
//    @Transactional
//    @TransactionScoped
    public Person save(Person person){
        boolean personCheck = getById(person.getId()).isPresent();
        Person p = null;
//        if (personCheck) {
//            personRepository.save(person);
//            return "update";
//        }
        try {
            mailservices.sendSimpleMessage(person.getEmail(), "Iqwal Application", "Hello, Welcome "+person.getName()+" Enjoy your journey! Thanks");

            return p = personRepository.save(person);
//            return "insert";
//            return true;
        } catch (Exception e) {
            System.out.println(e);
//            return "error";
            return p;
        }
    }
    
//    public void saveAll(){
//        List<Person> persons = new ArrayList<>();
//        persons.add(new Person("P001", "Fikri", "fikriridhwan@gmail.com", "MALE",23));
//        persons.add(new Person("P002", "Iqwal", "iqwal@email.com", "MALE",23));
//        persons.add(new Person("P003", "Nisa", "nisa@email.com", "FEMALE",23));
//        persons.add(new Person("P004", "Zakky", "zakky@email.com", "MALE",23));
//        persons.add(new Person("P005", "Aqira", "aqira.kelana@gmail.com", "MALE",23));
//        System.out.println(personRepository.saveAll(persons).toString());
//    }
    
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
            Person p = getById(id).get();
            System.out.println(p.toString());
            personRepository.delete(p);
            System.out.println(getById(id).get().toString()+" ini test");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
