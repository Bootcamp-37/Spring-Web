/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp37.bc37consume.services;

import com.bootcamp37.bc37consume.entities.Person;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author aqira
 */
@Service
public class PersonRestService {

    // UNIFORM RESOURCE IDENTIFIER
    @Value("${api.uri}")
    private String uri;
    
    //^ Equivalent
    private String url = "http://localhost:8085/api/";

    private RestTemplate restTemplate;

    @Autowired
    public PersonRestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //getAll
    public List<Person> getAll() {
        ResponseEntity<List<Person>> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Person>>() {
        });
        List<Person> result = response.getBody();
        return result;
    }

    //getbyid
    public Person getById(String id) {
        //"id" : "P001"
        Map<String, String> param = new HashMap<>();
        param.put("id", id);

        Person result = restTemplate.getForObject(uri + "/{id}", Person.class, param);
        return result;
    }

    public void save(Person person) {
        Person result = restTemplate.postForObject(uri, person, Person.class);
    }

    public void delete(String id) {
        Map<String, String> param = new HashMap<>();
        param.put("id", id);

        restTemplate.delete(uri + "/{id}", param);
    }
    
    public void update(Person person){
        Map<String, Object> params = new HashMap<>();
        params.put("id", person.getId());
        params.put("name", person.getName());
        params.put("email", person.getEmail());
        params.put("gender", person.getGender());
        params.put("age", person.getAge());
        
        restTemplate.put(uri, params);
    }
}
