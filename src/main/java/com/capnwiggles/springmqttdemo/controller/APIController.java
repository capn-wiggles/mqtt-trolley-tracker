package com.capnwiggles.springmqttdemo.controller;

import com.capnwiggles.springmqttdemo.domain.Trolley;
import com.capnwiggles.springmqttdemo.service.CurrentLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class APIController {

    private final CurrentLocationService service;

    @GetMapping(value = "/trolleys", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Trolley> getAllTrolleys() {
        return service.findAll();
    }
}
