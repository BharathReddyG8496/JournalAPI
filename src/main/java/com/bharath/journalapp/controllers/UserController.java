package com.bharath.journalapp.controllers;


import com.bharath.journalapp.Entities.User;
import com.bharath.journalapp.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserServices userServices;

    @DeleteMapping
    public ResponseEntity<Object> deleteUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
             userServices.deleteUser(username);
            return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody User user){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean b = userServices.updateUser(user,username);
        if(b)
            return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
