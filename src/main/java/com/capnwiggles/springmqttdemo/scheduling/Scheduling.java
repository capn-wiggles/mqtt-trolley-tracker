package com.capnwiggles.springmqttdemo.scheduling;

import com.capnwiggles.springmqttdemo.domain.Trolley;
import com.capnwiggles.springmqttdemo.service.CurrentLocationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class Scheduling {

    private final SimpMessagingTemplate template;
    private final CurrentLocationService service;
    private final ObjectMapper mapper;

    @Value("${scheduling.db-cleanup-threshold}")
    private Long dbEntityCleanupThreshold;

    @Scheduled(fixedDelayString = "${scheduling.db-clean-rate}")
    public void dbCleanup() {
        List<Trolley> trolleys = service.findAll();

        long currentTime = System.currentTimeMillis();

        List<Trolley> notUpdatedTrolleys = trolleys.stream()
                .filter(trolley -> (currentTime - trolley.getTimestamp().getTime()) > dbEntityCleanupThreshold )
                .collect(Collectors.toList());

        service.deleteAll(notUpdatedTrolleys);

        log.info("Removed {} database insertions", notUpdatedTrolleys.size());
    }

    @SneakyThrows
    @Scheduled(fixedDelayString = "${scheduling.send-message-rate}")
    public void pushStompAllTrolleys() {
        String destination = "/topic/trolleys";

        log.info("Pushed to {}", destination);

        String trolleysSerialized = mapper.writeValueAsString(service.findAll());
        template.convertAndSend(destination, trolleysSerialized);
    }
}
