package com.capnwiggles.springmqttdemo.controller;

import com.capnwiggles.springmqttdemo.domain.Trolley;
import com.capnwiggles.springmqttdemo.service.CurrentLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class APIController {

    private final CurrentLocationService service;

    @GetMapping(value = "/trolleys", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Trolley>> getAllTrolleys() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/trolleys/{board}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Trolley> getTrolleyByBoard(@PathVariable("board") Integer board) {
        return ResponseEntity.ok(service.findByBoard(board));
    }

    @GetMapping(value = "/routes/{route}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Trolley>> getAllTrolleysByRoute(@PathVariable("route") Integer route) {
        return ResponseEntity.ok(service.findByRoute(route));
    }
}
