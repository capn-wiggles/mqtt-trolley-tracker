package com.capnwiggles.springmqttdemo.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Configuration
@EnableScheduling
@ComponentScan(basePackages = "com.capnwiggles.springmqttdemo")
public class ApplicationConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean(name = "current_location_om")
    public ObjectMapper currentLocationObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

        return objectMapper;
    }

    @Bean(name = "simple_date_formatter")
    public DateFormat simpleDateFormat() {
        return new SimpleDateFormat();
    }

}
