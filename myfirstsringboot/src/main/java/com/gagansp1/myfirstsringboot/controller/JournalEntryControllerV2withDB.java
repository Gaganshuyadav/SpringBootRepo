package com.gagansp1.myfirstsringboot.controller;

import com.gagansp1.myfirstsringboot.entity.JournalEntry;
import com.gagansp1.myfirstsringboot.entity.User;
import com.gagansp1.myfirstsringboot.service.JournalEntryService;
import com.gagansp1.myfirstsringboot.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journalv2db")
public class JournalEntryControllerV2withDB {

    //In Spring Framework, @Autowired is an annotation that facilitates automatic dependency injection. It allows Spring to automatically resolve and inject collaborating beans into your objects, reducing the need for explicit configuration and making your code cleaner. @Autowired can be used on fields, constructors, and methods, enabling Spring to handle the injection of dependencies at various stages of the bean lifecycle.
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    //create new enteries
    //if same id then update otherwise create new
    @PostMapping("/create")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try{
            myEntry.setDate(LocalDateTime.now());
            System.out.println(myEntry);
            journalEntryService.saveEntry( myEntry, authentication.getName());
            return new ResponseEntity<>( myEntry, HttpStatus.CREATED);
        }
        catch (Exception err){
            throw new RuntimeException("An error occurred while saving the entry");

        }
    }

    //get all enteries
    @GetMapping("/all")
    public ResponseEntity<?> getAllEnteries(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return journalEntryService.getAll( authentication.getName());
    }

    //get enteries by id
    @GetMapping("/id/{myId}")
    public ResponseEntity<Optional<JournalEntry>> getEntryById(@PathVariable ObjectId myId){
        Optional<JournalEntry> journalEntry = journalEntryService.getById(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<Optional<JournalEntry>>( journalEntry, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>( journalEntry, HttpStatus.NOT_FOUND);
        }
    }

    //delete enteries by id
    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        journalEntryService.deleteById( myId, authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //upadte enteries
    @PutMapping("/update/id/{myId}")
    public ResponseEntity<?> updateEntryById( @PathVariable ObjectId myId, @RequestBody JournalEntry newEntry ){
        JournalEntry old = journalEntryService.getById(myId).orElse(null);
        if(old!=null){
            //check title if not null or empty or ""
            old.setTitle( newEntry.getTitle()!=null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle() );
            //check title if not null or empty or ""
            old.setContent( newEntry.getContent()!=null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : old.getContent());

            journalEntryService.save(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }

        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }


    //get all enteries by username
    @GetMapping("/enteries/user")
    public User getAllEnteriesOfUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUserName( authentication.getName());
        System.out.println(user);
        return user;
    }



}


