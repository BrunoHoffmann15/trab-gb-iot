package com.iot.tempcontrol.consumer.repositories.adapters;

import com.iot.tempcontrol.consumer.domain.DeviceSensorTemperature;
import com.iot.tempcontrol.consumer.repositories.entities.Temperature;

public class TemperatureAdapter {
    public static Temperature convertToTemperature(DeviceSensorTemperature deviceSensorTemperature) {
        return new Temperature(
                deviceSensorTemperature.idDevice,
                deviceSensorTemperature.temperature,
                deviceSensorTemperature.createdAt
        );
    }
}
