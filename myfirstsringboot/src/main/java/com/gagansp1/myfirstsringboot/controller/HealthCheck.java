package com.gagansp1.myfirstsringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HealthCheck {

    @GetMapping("/")
    public String local(){
        return "i am running";
    }

    @GetMapping("/info/hello")
    public String hai(){
        return "hai, nice to meet you";
    }

    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";
    }

}
