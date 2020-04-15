package com.capnwiggles.springmqttdemo.config.scheduling;

import com.capnwiggles.springmqttdemo.service.CurrentLocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseCleanupJob {

    private final CurrentLocationService service;

    @Scheduled(cron = "0 */3 * ? * *")
    public void cleanup() throws InterruptedException {
        log.info("cleanup job");
    }
}
