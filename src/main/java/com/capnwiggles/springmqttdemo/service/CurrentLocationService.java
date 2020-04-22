package com.capnwiggles.springmqttdemo.service;

import com.capnwiggles.springmqttdemo.domain.Trolley;
import com.capnwiggles.springmqttdemo.repository.CurrentLocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        return repository.findAll();
    }

    public Trolley findByBoard(Integer board) {
        return repository.findByBoard(board);
    }

    public List<Trolley> findByRoute(Integer route) {
        return repository.findByRoute(route);
    }

    public void deleteAll(List<Trolley> trolleys) {
        repository.deleteAll(trolleys);
    }

}

