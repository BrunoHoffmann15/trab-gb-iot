package com.iot.tempcontrol.api.models;

import com.iot.tempcontrol.api.domains.DeviceSensorTemperature;

import java.time.LocalDateTime;

public class DeviceSensorTemperatureCreateRequest {
    public LocalDateTime createdAt;
    public float temperature;

    public DeviceSensorTemperature convert(String idDevice) {
        var info = new DeviceSensorTemperature();

        info.idDevice = idDevice;
        info.temperature = temperature;
        info.createdAt = createdAt;

        return info;
    }
}
