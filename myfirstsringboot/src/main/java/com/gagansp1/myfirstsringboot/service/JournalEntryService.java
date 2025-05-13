package com.gagansp1.myfirstsringboot.service;

import com.gagansp1.myfirstsringboot.entity.JournalEntry;
import com.gagansp1.myfirstsringboot.entity.User;
import com.gagansp1.myfirstsringboot.repository.JournalEntryRepo;
import com.gagansp1.myfirstsringboot.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    //logging in spring boot
    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

    //In Spring Framework, @Autowired is an annotation that facilitates automatic dependency injection. It allows Spring to automatically resolve and inject collaborating beans into your objects, reducing the need for explicit configuration and making your code cleaner. @Autowired can be used on fields, constructors, and methods, enabling Spring to handle the injection of dependencies at various stages of the bean lifecycle.
    @Autowired
    private JournalEntryRepo journalEntryRepository;

    @Autowired
    private UserRepository userRepo;

    //if same id then update otherwise create new
    @Transactional //required , for atomicity:- the entire process takes place at once or doesn't happen at all
    public void saveEntry(JournalEntry journalEntry, String userName){
        //find user
        User user = userRepo.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        //save journal entry
        JournalEntry journalE =  journalEntryRepository.save(journalEntry);

        //add journal entry in user db
        user.getJournalEnteries().add(journalE);
        userRepo.save(user);

    }

    public ResponseEntity<?> getAll( String userName){
        User user = userRepo.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEnteries();
        logger.info("INFO sahi chal rha hai");
        logger.error("ERROR sahi chal rha hai");
        logger.warn("WARN sahi chal rha hai");
        logger.debug("DEBUG chal rha hai");
        logger.trace("TRACE chal rha hai");
        if( all!=null && !all.isEmpty()){
            return new ResponseEntity<>( all, HttpStatus.OK);
        }

        return  new ResponseEntity<>( HttpStatus.BAD_REQUEST);
    }

    public Optional<JournalEntry> getById(ObjectId id){
        return journalEntryRepository.findById(id);
    }


    // update
    public void save(JournalEntry journalEntry){
        //find user
        journalEntry.setDate(LocalDateTime.now());
        //save journal entry
        JournalEntry journalE =  journalEntryRepository.save(journalEntry);

    }

    public boolean deleteById( ObjectId id, String userName){
        User user = userRepo.findByUserName(userName);
        user.getJournalEnteries().removeIf(x->x.getId().equals(id));
        userRepo.save(user);
        journalEntryRepository.deleteById(id);
        return true;
    }


}


// controller --> service --> repository

