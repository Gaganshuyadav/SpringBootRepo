
package com.gagansp1.myfirstsringboot.controller;
import java.util.*;
import java.util.List;

import com.gagansp1.myfirstsringboot.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping add mapping to the whole class
@RequestMapping("/journal")
public class JournalEntryController {

    private final  Map<ObjectId, JournalEntry> journalEnteries = new HashMap<>();

    @GetMapping("/getallenteries")
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEnteries.values());
    }

    //methods inside a controller class should be public so that they can be accessed and invoked by the spring framework or external HTTP requests
    //by default mapping is localhost:8080/journal GET
    @GetMapping
    private String getStrings(){
        return "the whole string is here";
    }

    @GetMapping("/**")
    public String routeHandler(){
        return "Handle All Routes";
    }

    // localhost:8080/journal POST
    //@RequsetBody :- it's like saying, "Hey Spring", please take the data from the request and turn it into a java object that i can use in my code
    @PostMapping("/create")
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        journalEnteries.put( myEntry.getId(), myEntry);
        return true;
    }

    @GetMapping("/id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId){
        return journalEnteries.get(myId)!=null ? journalEnteries.get(myId) : null;
    }

    @DeleteMapping("/id/{myId}")
    public JournalEntry deleteJournalEntryById(@PathVariable ObjectId myId){
        return journalEnteries.remove(myId);
    }

    @PutMapping("/id/{myId}")
    public JournalEntry updateJournalEntryById( @PathVariable ObjectId myId, @RequestBody JournalEntry myEntry){
        journalEnteries.put( myId, myEntry);
        return journalEnteries.get(myId);
    }


}










