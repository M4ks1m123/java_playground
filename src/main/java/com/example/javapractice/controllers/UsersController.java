package com.example.javapractice.controllers;

import com.example.javapractice.models.UserEntity;
import com.example.javapractice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UsersController {


    UserService userService;

    public UsersController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(path="/users/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable("id") Long id){
        Optional<UserEntity> resultUser = userService.findUserById(id);
        if(resultUser.isPresent())
            return new ResponseEntity<>(resultUser.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/users")
    public ResponseEntity<UserEntity> postUser(@RequestBody UserEntity userEntity){
        UserEntity savedUser = userService.save(userEntity);
//        System.out.println(userEntity.getUsername());
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}
