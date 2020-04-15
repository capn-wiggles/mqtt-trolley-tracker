package com.capnwiggles.springmqttdemo.domain;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // TODO: make something happen
        return Optional.empty();
    }

}
