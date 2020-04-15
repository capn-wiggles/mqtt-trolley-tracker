package com.capnwiggles.springmqttdemo.mqtt;

import com.capnwiggles.springmqttdemo.domain.Trolley;
import com.capnwiggles.springmqttdemo.service.CurrentLocationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class MqttCallbackImpl implements MqttCallback {

    @Qualifier("current_location_om")
    private final ObjectMapper objectMapper;

    private final CurrentLocationService currentLocationService;

    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        log.info("[" + s + "]: " + mqttMessage.toString());

        Trolley trolley = new Trolley();
        trolley = objectMapper.readValue(mqttMessage.toString(), Trolley.class);

        currentLocationService.saveTelemetry(trolley);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
