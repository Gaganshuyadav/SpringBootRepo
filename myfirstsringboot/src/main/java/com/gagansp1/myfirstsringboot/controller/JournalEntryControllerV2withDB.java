package com.gagansp1.myfirstsringboot.controller;

import com.gagansp1.myfirstsringboot.entity.JournalEntry;
import com.gagansp1.myfirstsringboot.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    //if same id then update otherwise create new
    @PostMapping("/create")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
        try{
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity<>( myEntry, HttpStatus.CREATED);
        }
        catch (Exception err){
         return  new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public List<JournalEntry> getAllEnteries(){
        return journalEntryService.getAll();
    }

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

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{myId}")
    public ResponseEntity<?> updateEntryById( @PathVariable ObjectId myId, @RequestBody JournalEntry newEntry ){
        JournalEntry old = journalEntryService.getById(myId).orElse(null);
        if(old!=null){
            //check title if not null or empty or ""
            old.setTitle( newEntry.getTitle()!=null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle() );
            //check title if not null or empty or ""
            old.setContent( newEntry.getContent()!=null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());

            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }

        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }



}


