package com.capnwiggles.springmqttdemo.scheduling;

import com.capnwiggles.springmqttdemo.domain.Trolley;
import com.capnwiggles.springmqttdemo.service.CurrentLocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseCleanupJob {

    private final CurrentLocationService service;

    @Value("${scheduling.db-cleanup-threshold}")
    private Long dbEntityCleanupThreshold;

    @Scheduled(fixedDelayString = "${scheduling.db-clean-rate}")
    public void cleanup() throws InterruptedException {
        List<Trolley> trolleys = service.findAll();

        long currentTime = System.currentTimeMillis();

        // doesn't work :(
        List<Trolley> notUpdatedTrolleys = trolleys.stream()
                .filter(trolley -> (currentTime - trolley.getTimestamp().getTime()) > dbEntityCleanupThreshold )
                .collect(Collectors.toList());

        service.deleteAll(notUpdatedTrolleys);
    }
}
