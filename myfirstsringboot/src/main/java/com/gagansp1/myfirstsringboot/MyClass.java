package com.gagansp1.myfirstsringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyClass {

//    /*Autowired:- spring give me the Dog object  ( it is called dependency injection means MyClass depends on Dog)*/
//    @Autowired
//    private Dog (dog)




    //it creates endpoint
    @GetMapping("abc")
    public String sayHello(){
        return "Hello";
    }
}
