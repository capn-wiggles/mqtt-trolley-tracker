package com.capnwiggles.springmqttdemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebAppController {

    @GetMapping("/")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("<h1>Home Page</h1>");
    }
}