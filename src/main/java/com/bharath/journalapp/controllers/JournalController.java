package com.bharath.journalapp.controllers;

import com.bharath.journalapp.Entities.Journal;
import com.bharath.journalapp.Services.JournalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/journals")
public class JournalController {

    @Autowired
    JournalServices journalServices;

    @GetMapping("/find")
    public ResponseEntity<Object> findAllJournalsOfUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Journal> allJournals = journalServices.getAllJournals(username);
            if(allJournals.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity<>(allJournals, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addJournalToUser(@RequestBody Journal journalEntry){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean journal = journalServices.addJournalToUser(journalEntry,username);
        if(journal==false){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else{
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> returnJournalByID(@PathVariable long id){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Journal journalByID = journalServices.findJournalByID(id,username);
        if(journalByID == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity<>(journalByID,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateJournal(@RequestBody Journal newJournal,@PathVariable long id){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean b = journalServices.updateJournal(newJournal, id,username);
        if(b)
            return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteJournal(@PathVariable long id){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean b = journalServices.DeleteJournal(id,username);
        if(b) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
