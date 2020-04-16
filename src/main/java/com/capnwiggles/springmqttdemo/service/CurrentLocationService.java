package com.capnwiggles.springmqttdemo.service;

import com.capnwiggles.springmqttdemo.domain.Trolley;
import com.capnwiggles.springmqttdemo.repository.CurrentLocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CurrentLocationService {

    @Autowired
    private CurrentLocationRepository repository;

    public Trolley saveTelemetry(Trolley telemetry) {
        repository.save(telemetry);
        return telemetry;
    }

    public List<Trolley> findAll() {
        log.info(repository.findAll().toString());
        return repository.findAll();
    }

    public void deleteAll(List<Trolley> trolleys) {
        repository.deleteAll(trolleys);
    }

}

