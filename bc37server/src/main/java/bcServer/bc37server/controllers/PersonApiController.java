/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bcServer.bc37server.controllers;

import bcServer.bc37server.entities.Person;
import bcServer.bc37server.services.PersonService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
 * @author Laila
 */

       @RequestMapping("api")
    @RestController
    
public class PersonApiController {
       PersonService personService;
       @Autowired

    public PersonApiController(PersonService personService) {
        this.personService = personService;
    }
       @GetMapping("ch")
       public String check(){
           return "check";
       }
//        @GetMapping("")
//public String index( Model model){
//   model.addAttribute("person", new Person());
//    model.addAttribute("people", personService.getAll());
//   
//    return "index";
//}
    @GetMapping("")
    public List<Person> getAll(){
//        personService.saveAll();
     return personService.getAll();
    }
//    @GetMapping("")
//    public String getAll(Model model){
//        model.addAttribute("people",service.getAll());
//        return "index1";
//    }
    @GetMapping("ad")
    public String admin(){
//        personService.saveAll();
//        return personService.getAll();
return "admin";
    }
    @GetMapping("{id}")
    public Person getById(@PathVariable("id") String id){
        return personService.getById(id).get();
    }
    @PostMapping("")
    public ResponseEntity<String> insert (@RequestBody Person person){
        String result= personService.save(person);
        //berhasil
        if(result.equals("success")){
            return ResponseEntity.ok().body("Insert Berhasil!");
        }
        else if(result.equals("cannot update")){
            return ResponseEntity.status(409).body("Tidak dapat update, silahkan gunakan method lain!");
        }
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Gagal memasukkan");
    }
        
    @PutMapping("")
    public boolean update(@RequestBody Person person){
        return personService.update(person);
    }
    @DeleteMapping("{id}")
    public boolean delete(@PathVariable @Valid String id){
        return personService.delete(id);
    }

}
