package com.example.quanlybanhang.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/hello")
    public ResponseEntity<?> getGreeting(){
        return ResponseEntity.ok("helloo");
    }
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(){
        return ResponseEntity.ok("profile");
    }
}
