package com.gagansp1.myfirstsringboot.controller;

import com.gagansp1.myfirstsringboot.entity.User;
import com.gagansp1.myfirstsringboot.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        if(user.getUserName()==null){
            return null;
        }
        System.out.println(user);

        return userService.saveEntry(user);
    }


    @GetMapping("/id/{userId}")
    public Optional<User> getById(@PathVariable ObjectId userId){
        return userService.getById(userId);
    }

    @DeleteMapping("/id/{userId}")
    public boolean deleteUserById(@PathVariable ObjectId userId){
        return userService.deleteById(userId);
    }

    @PutMapping("/put")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        String userName = authentication.getName();

        //get user
        User userInDb = userService.getByUserName(userName);
        if(userInDb!=null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/del")
    public ResponseEntity<?> deleteUserByUsingName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.deleteByUserName( authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}