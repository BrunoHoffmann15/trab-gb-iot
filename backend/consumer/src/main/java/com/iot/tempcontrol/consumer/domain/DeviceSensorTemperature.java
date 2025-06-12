package com.iot.tempcontrol.consumer.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class DeviceSensorTemperature {

    @JsonProperty("temperature")
    public float temperature;

    @JsonProperty("idDevice")
    public String referencedDevice;

    public String idDevice;

    public LocalDateTime createdAt;

    public DeviceSensorTemperature(@JsonProperty("idDevice") String referencedDevice,
                                   @JsonProperty("temperature") float temperature) {
        this.temperature = temperature;
        this.referencedDevice = referencedDevice;
        this.createdAt = LocalDateTime.now();
    }

    public DeviceSensorTemperature(String idDevice,
                                   String referencedDevice,
                                   float temperature,
                                   LocalDateTime createdAt) {
        this.temperature = temperature;
        this.referencedDevice = referencedDevice;
        this.idDevice = idDevice;
        this.createdAt = createdAt;
    }

    public float getTemperature() {
        return temperature;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public boolean isTimeOnRange() {
        return createdAt.isAfter(LocalDateTime.now().minusMinutes(5555555));
    }
}
