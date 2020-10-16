/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp37.testcrud.controllers;

import bootcamp37.testcrud.entities.Person;
import bootcamp37.testcrud.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Deo Lahara
 */


//@Controller
//public class PersonController {
//    PersonServices service;
//    SendEmailServices mailservices;
//    
//    
//    @Autowired
//    public PersonController(PersonServices service, SendEmailServices mailservices) {
//        this.service = service;
//        this.mailservices=mailservices;
//    }
//    
//    
//    @GetMapping("")
//    public List<Person> getAll(){
//        service.saveAll();
//        return service.getAll();
//    }
//    
//    
//
//    @GetMapping("{id}")
//    public Person getById(@PathVariable String id){
//        return service.getById(id).get();
//    }
//        
//    //Search Keyword
//    @ResponseBody
//    @RequestMapping("/search/{keyword}")
//    public List<Person> search (@PathVariable @Valid String keyword){
//        return service.search(keyword);
//    }
//       
//    @PostMapping("")
//    public ResponseEntity<String> insert (@RequestBody Person person){
//        String result = service.save(person);
//        if(result.equals("Success")){
//            return ResponseEntity
//                    .ok()
//                    .body("Insert Berhasil");
//        }
//        else if(result.equals("Cannot Update")){
//            return ResponseEntity
//                    .status(409)
//                    .body("Tidak dapat update, silahkan menggunakan method lain ");
//        }
//    return ResponseEntity
//            .status(HttpStatus.BAD_GATEWAY)
//            .body("Gagal Memasukkan");
//    }
//    
//    @PutMapping("")
//    public boolean update(@RequestBody Person person){
//        return service.update(person);
//    }
//    
//    @DeleteMapping("{id}")
//    public boolean tesDelete(@PathVariable String id){
//        return service.delete(id);
//    }
//    
//    
//    @ResponseBody
//    @GetMapping("saves")
//    public String simpanSemua(){
//        service.saveAll();
//        return "Simpan berhasil";
//    }
//    
    
//    @GetMapping("Sendmail")
//    public boolean kirimEmail(){
//        
//        try {
//           mailservices.sendSimpleMessage("aqira.kelana@gmail.com", "Hello, Iqwal Akmar", "Iqwal Akmar Email");
//            System.out.println("Test Email Ka Aqira");
//           return true;
//        } catch (Exception e) {
//            return false;
//        }
//    
//    }

@Controller
public class PersonController {
  PersonServices service;
  
  @Autowired
  public PersonController(PersonServices service) {
    this.service = service;
  }
  
  @GetMapping({""})
  public String index() {
    System.out.println(this.service.getAll());
    return "index";
  }
  
//  @ResponseBody
//  @GetMapping({"update"})
//  public boolean update() {
//    Person person = new Person();
//    person.setId("P001");
//    person.setAge(Integer.valueOf(23));
//    person.setGender("Male");
//    return this.service.update(person);
//  }
  
  @ResponseBody
  @GetMapping({"insert"})
  public String tesInsert() {
    Person person = new Person();
    person.setId("P001");
    person.setName("Aqira Kelana");
    person.setEmail("aqira.kelana@gmail.com");
    person.setAge(Integer.valueOf(23));
    return this.service.save(person);
  }
}
