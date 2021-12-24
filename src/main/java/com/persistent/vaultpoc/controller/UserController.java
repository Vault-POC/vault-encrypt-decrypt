package com.persistent.vaultpoc.controller;

import com.persistent.vaultpoc.model.User;
import com.persistent.vaultpoc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController( UserService userService){this.userService = userService;}

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id){
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/add-user")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User newUser = userService.addNewUser(user);
        return new ResponseEntity<>(newUser,HttpStatus.CREATED);
    }

}
