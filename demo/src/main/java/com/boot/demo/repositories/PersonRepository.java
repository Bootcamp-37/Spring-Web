/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boot.demo.repositories;

import com.boot.demo.entities.Person;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ZFH
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, String>{
    List<Person> findByNameContainingOrGenderContainingOrEmailContainingOrIdContaining(String name, String gender, String email, String id);
}
