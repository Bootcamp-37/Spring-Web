/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp37.bc37consume.services;


import com.bootcamp37.bc37consume.entities.Person;
import java.net.URLEncoder;
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
 * @author Laila
 */
@Service
public class PersonRestService {
//    @Value("$(api.uri)")
//    private String uri;
//     URLEncoder.encode();
    private String uri= "http://localhost:8085/api";
//    URLEncoder.encode(uri); 
    private RestTemplate restTemplate;
@Autowired
    public PersonRestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public List<Person> getAll(){
        ResponseEntity<List<Person>> response = restTemplate.exchange(
                uri, 
                HttpMethod.GET, 
                null, 
                new ParameterizedTypeReference<List<Person>>(){
            
        });
        List<Person> result= response.getBody();
        return  result;
    }
    public Person getById (String id){
        //"id":"P001"
        Map<String,String> param = new HashMap<>();
                param.put("id", id);
                try{
                     Person result= restTemplate.getForObject(uri+"/getById/{id}", Person.class,param);
                return result;
                } catch (Exception e){
                    return null;
                }
               
    }
//    public void save (Person person){
//       Person result= restTemplate.postForObject(uri,person, Person.class);
//    }
    
     public String save(Person person){
        if (getById(person.getId()) != null) {
            restTemplate.put(uri, person, Person.class);
            return "update";
        }else{
            Person result = restTemplate.postForObject(uri, person, Person.class);
            if (result == null) {
                return "error";
            }
            return "insert";
        }
        
    }
    public void delete (String id){
        Map<String, String> param = new HashMap<>();
        param.put("id", id);
        restTemplate.delete(uri+"/{id}", param);
    }
    public void update(Person person){
        Map<String, Object> params= new HashMap<>();
        params.put("id", person.getId());
        params.put("name", person.getName());
        params.put("email", person.getEmail());
        params.put("gender", person.getGender());
        params.put("age", person.getAge());
        restTemplate.put(uri, params);
    }
    
}
