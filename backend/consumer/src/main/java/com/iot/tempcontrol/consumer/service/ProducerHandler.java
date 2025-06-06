package com.iot.tempcontrol.consumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iot.tempcontrol.consumer.gateway.MqttGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProducerHandler {
    @Autowired
    public MqttGateway mqttGateway;

    public ObjectMapper objectMapper = new ObjectMapper();

    public void handle(Object message, String topic) {
        try {
            var stringMessage = objectMapper.writeValueAsString(message);
            mqttGateway.sendToMqtt(stringMessage, topic);
            log.info("Message sent to topic {}: {}", topic, stringMessage);
        } catch (Exception e) {
            log.error(String.format("An error ocurred while publishing to topic %s", topic), e);
        }
    }
}