package com.gagansp1.myfirstsringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping("/")
    public String local(){
        return "i am running";
    }

    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";
    }

}
