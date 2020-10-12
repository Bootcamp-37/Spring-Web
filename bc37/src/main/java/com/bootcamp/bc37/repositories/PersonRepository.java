/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.bc37.repositories;

import com.bootcamp.bc37.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aqira
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, String> { //Java Persistence Application Programming Interface
    
}
