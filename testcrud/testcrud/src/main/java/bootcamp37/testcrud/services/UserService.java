/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootcamp37.testcrud.services;

import bootcamp37.testcrud.Repositories.RoleRepository;
import bootcamp37.testcrud.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class UserService {
    
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserRepository urRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserRepository urRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.urRepository = urRepository;
    }
    
    
    
    
}
