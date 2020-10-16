/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp37.testcrud.Repositories;

import bootcamp37.testcrud.entities.Person;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
    List<Person> findByNameContainingOrGenderContainingOrEmailContainingOrIdContaining(String name, String gender, String email, String id);
}
