package com.cabman.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/cabs/v1")
public class CabsController {

    @GetMapping("/health")
    public String healthCheck(){
        return "Working Fine";
    }
}
