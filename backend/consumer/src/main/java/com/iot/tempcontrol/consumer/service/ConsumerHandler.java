package com.iot.tempcontrol.consumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iot.tempcontrol.consumer.domain.DeviceSensorTemperature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerHandler implements MessageHandler {

    private final ObjectMapper mapper = new ObjectMapper();
    private final TemperatureHandler temperatureHandler;

    @Autowired
    public ConsumerHandler(TemperatureHandler temperatureHandler) {
        this.temperatureHandler = temperatureHandler;
    }

    @Override
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handleMessage(Message<?> message) throws MessagingException {
        String payload = (String) message.getPayload();
        String topic = (String) message.getHeaders().get("mqtt_receivedTopic");

        log.info("Message received: {}", message);

        try {
            if (topic.contains("temperature")) {
                var temperature = mapper.readValue(payload, DeviceSensorTemperature.class);
                temperatureHandler.handle(temperature);
            }
        } catch (Exception e) {
            log.error("An error occurred while receiving message", e);
        }

    }
}
