package bootcamp37.testcrud.controllers;

import bootcamp37.testcrud.entities.Person;
import bootcamp37.testcrud.services.PersonServices;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api")
@RestController
public class PersonApiController {
    PersonServices service;
  
  @Autowired
  public PersonApiController(PersonServices service) {
    this.service = service;
  }
  
  @GetMapping("")
  public List<Person> getAll() {
//    this.service.saveAll();
    return this.service.getAll();
  }
  
  @GetMapping("{id}")
  public Person getById(@PathVariable String id) {
    return this.service.getById(id).get();
  }
  
  @PostMapping("")
  public ResponseEntity<String> insert(@RequestBody Person person) {
    String result = service.save(person);
    if (result.equals("Success")){
      return 
        ResponseEntity.ok()
        .body(""); }
    else if (result.equals("Updated")){
      return 
        ResponseEntity.ok()
        .body(""); }
    else {
    return 
      ResponseEntity.status(HttpStatus.BAD_GATEWAY)
      .body("Error");}
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
