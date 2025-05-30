package com.iot.tempcontrol.consumer.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class DeviceSensorTemperature {

    @JsonProperty("temperature")
    public float temperature;

    @JsonProperty("idDevice")
    public String idDevice;

    public LocalDateTime createdAt;

    public DeviceSensorTemperature(@JsonProperty("idDevice") String idDevice,
                                   @JsonProperty("temperature") float temperature) {
        this.temperature = temperature;
        this.idDevice = idDevice;
        this.createdAt = LocalDateTime.now();
    }

    public float getTemperature() {
        return temperature;
    }

    public boolean isTimeOnRange() {
        return createdAt.isAfter(LocalDateTime.now().minusMinutes(15));
    }
}
