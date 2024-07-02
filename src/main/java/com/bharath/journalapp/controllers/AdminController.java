package com.bharath.journalapp.controllers;

import com.bharath.journalapp.Entities.User;
import com.bharath.journalapp.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserServices userServices;

    @GetMapping("/get-all-users")
    public ResponseEntity<Object> getAllUsers(){
        List<User> users = userServices.returnUsers();
        if(users==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @PostMapping("/add-new-admin")
    public ResponseEntity<Object> addNewAdmin(@RequestBody User user){
        userServices.addNewAdmin(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    } 
}
