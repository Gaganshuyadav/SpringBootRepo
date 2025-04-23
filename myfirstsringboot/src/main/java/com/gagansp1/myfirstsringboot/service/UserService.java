package com.gagansp1.myfirstsringboot.service;

import com.gagansp1.myfirstsringboot.entity.JournalEntry;
import com.gagansp1.myfirstsringboot.entity.User;
import com.gagansp1.myfirstsringboot.repository.JournalEntryRepo;
import com.gagansp1.myfirstsringboot.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {


    //In Spring Framework, @Autowired is an annotation that facilitates automatic dependency injection. It allows Spring to automatically resolve and inject collaborating beans into your objects, reducing the need for explicit configuration and making your code cleaner. @Autowired can be used on fields, constructors, and methods, enabling Spring to handle the injection of dependencies at various stages of the bean lifecycle.
    @Autowired
    private UserRepository userRepository ;

//    if same id then update otherwise create new
    public void saveEntry(User user){
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> getById(ObjectId id){
        return userRepository.findById(id);
    }

    public boolean deleteById(ObjectId id){
        userRepository.deleteById(id);
        return true;
    }
}
