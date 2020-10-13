/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bcServer.bc37server.repositories;

import bcServer.bc37server.entities.Person;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Laila
 */

    @Repository
public interface PersonRepository extends JpaRepository<Person, String>{
    @Query("SELECT p FROM Person p WHERE p.name like %?1% "
            + "or p.id like %?1% "
            + "or p.email like %?1% "
            + "or p.age like %?1% "
            + "or p.gender like %?1% ")
    public List<Person> search(String keyword);
    
}
