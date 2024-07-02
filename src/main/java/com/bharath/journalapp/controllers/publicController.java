package com.bharath.journalapp.controllers;

import com.bharath.journalapp.Entities.User;
import com.bharath.journalapp.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/public")
public class publicController {

    @Autowired
    UserServices userServices;
    @PostMapping("/addUser")
    public ResponseEntity<Object> addUser(@RequestBody User user){
        user.setRoles(Arrays.asList("USER"));
        User user1 = userServices.addNewUser(user);

        if(user1==null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            URI url= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user1.getId()).toUri();
            return ResponseEntity.created(url).build();
        }
    }
    @GetMapping
    public ResponseEntity<Object> getAllUsers(){
        List<User> users = userServices.returnUsers();
        if(users==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else return new ResponseEntity<>(users,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable long id){
        User userById = userServices.findUserById(id);
        if(userById==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity<>(userById,HttpStatus.OK);
    }

}
