package com.gagansp1.myfirstsringboot.controller;

import com.gagansp1.myfirstsringboot.entity.User;
import com.gagansp1.myfirstsringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    public UserService userService;


    @GetMapping("/get-all")
    public ResponseEntity<?> getAllUsers(){
        List<User> users =  userService.getAllUsersForAdmin();
        if( users!=null && users.size() > 1) {
            return new ResponseEntity<>( users,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-new-admin")
    public User createNewUser(@RequestBody User user){
        return userService.createNewAdmin(user);


    }



}









