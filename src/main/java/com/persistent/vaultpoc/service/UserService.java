package com.persistent.vaultpoc.service;

import com.persistent.vaultpoc.execption.UserNotFoundException;
import com.persistent.vaultpoc.model.User;
import com.persistent.vaultpoc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.attribute.UserPrincipalNotFoundException;

public class UserService {

    private final UserRepository userRepository;
    
    @Autowired
    public UserService(UserRepository userRepository) {this.userRepository = userRepository; }
    
    public User getUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User is with id:" + id + " was not found!"));
        return user;
    }
}
