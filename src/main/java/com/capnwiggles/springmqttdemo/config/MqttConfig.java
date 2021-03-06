package com.capnwiggles.springmqttdemo.config;

import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.extern.slf4j.Slf4j;

@Configuration
@PropertySource("classpath:mqtt.properties")
@Slf4j
public class MqttConfig {

    @Bean
    public MqttConnectOptions options(
            @Value("${mqtt.options.automaticReconnect}") boolean automaticReconnect,
            @Value("${mqtt.options.cleanSession}") boolean cleanSession,
            @Value("${mqtt.options.connectionTimeout}") int connectionTimeout,
            @Value("${mqtt.options.keepAliveInterval}") int keepAliveInterval) {
        MqttConnectOptions options = new MqttConnectOptions();

        options.setAutomaticReconnect(automaticReconnect);
        options.setCleanSession(cleanSession);
        options.setConnectionTimeout(connectionTimeout);
        options.setKeepAliveInterval(keepAliveInterval);

        String optionsString = String.format("\n[CONN_OPTIONS]\n" +
                        "\tAutomatic reconnect: %b\n" +
                        "\tClean session: %b\n" +
                        "\tConnection timeout time: %d\n" +
                        "\tKeep alive interval: %d\n",
                automaticReconnect, cleanSession, connectionTimeout, keepAliveInterval);
        log.info(optionsString);

        return options;
    }

    @Bean
    public MqttClientPersistence persistence() {
        return new MqttDefaultFilePersistence(System.getProperty("user.dir") + "/mqtt_locks");
    }

    @Bean
    public IMqttAsyncClient client(@Value("${mqtt.config.URI}") String URI,
                                   MqttCallback callback, MqttClientPersistence persistence) {

        try {
            IMqttAsyncClient client = new MqttAsyncClient(URI, MqttAsyncClient.generateClientId(), persistence);

            client.setCallback(callback);

            log.info("\nMqttAsyncClient created");

            return client;
        } catch (MqttException e) {
            log.error("\nCould not create MqttClient");
        }

        return null;
    }

    @Bean
    public IMqttToken mqttToken(IMqttAsyncClient client) {
        try {
            IMqttToken token = client.connect();
            token.waitForCompletion();

            client.subscribe("telemetry/route/+", 0);

            log.info("Connected to the broker");

            return token;
        } catch (MqttException e) {
            log.error("Error connecting to the broker");
        }

        return null;
    }
}
