package com.capnwiggles.springmqttdemo.controller;

import com.capnwiggles.springmqttdemo.service.CurrentLocationService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class STOMPController {

    private final CurrentLocationService service;
    private final ObjectMapper mapper;

    @MessageMapping("/trolley-telemetry")
    @SendTo("/topic/trolleys")
    public String allTrolleys() throws JsonProcessingException {
        String trolleysSerialized = mapper.writeValueAsString(service.findAll());
        return trolleysSerialized;
    }
}
