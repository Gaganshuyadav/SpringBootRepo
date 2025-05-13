package com.gagansp1.myfirstsringboot.service;

import com.gagansp1.myfirstsringboot.entity.User;
import com.gagansp1.myfirstsringboot.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//When you annotate a class with @Component, you are essentially telling Spring that this class should be instantiated and managed as a bean
@Component
@Slf4j
public class UserService {


    //In Spring Framework, @Autowired is an annotation that facilitates automatic dependency injection. It allows Spring to automatically resolve and inject collaborating beans into your objects, reducing the need for explicit configuration and making your code cleaner. @Autowired can be used on fields, constructors, and methods, enabling Spring to handle the injection of dependencies at various stages of the bean lifecycle.
    @Autowired
    private UserRepository userRepository ;


    // final keyword:- to declare constants, prevent method overriding, and prevent inheritance

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //if same id then update otherwise create new
    public User saveEntry(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("New User Created Successfully"); //we can also log with this( without instance)
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
        return user;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> getById(ObjectId id){
        return userRepository.findById(id);
    }

    public User getByUserName(String userName){
        return userName.isEmpty() ? null : userRepository.findByUserName(userName) ;
    }

    public boolean deleteById(ObjectId id){
        userRepository.deleteById(id);
        return true;
    }

    public boolean deleteByUserName(String userName){
        userRepository.deleteByUserName(userName);
        return true;
    }

    //admin
    public List<User> getAllUsersForAdmin(){
        return userRepository.findAll();
    }

    public User createNewAdmin( User user){
        user.setPassword( passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        return userRepository.save(user);
    }

}
