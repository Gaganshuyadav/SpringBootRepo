package com.gagansp1.myfirstsringboot.service;

import com.gagansp1.myfirstsringboot.entity.JournalEntry;
import com.gagansp1.myfirstsringboot.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    //In Spring Framework, @Autowired is an annotation that facilitates automatic dependency injection. It allows Spring to automatically resolve and inject collaborating beans into your objects, reducing the need for explicit configuration and making your code cleaner. @Autowired can be used on fields, constructors, and methods, enabling Spring to handle the injection of dependencies at various stages of the bean lifecycle.
    @Autowired
    private JournalEntryRepo journalEntryRepository;

    //if same id then update otherwise create new
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public boolean deleteById(ObjectId id){
        journalEntryRepository.deleteById(id);
        return true;
    }


}


// controller --> service --> repository

