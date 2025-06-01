package com.iot.tempcontrol.consumer.domain;

import java.util.List;
import java.util.Optional;

public class Device {
    public String idDevice;
    public List<DeviceSensorTemperature> deviceSensorTemperatureList;

    public Device(List<DeviceSensorTemperature> deviceSensorTemperatureList) {
        this.deviceSensorTemperatureList = deviceSensorTemperatureList;
    }

    public Optional<DeviceSensorTemperature> getLastTemperature() {
        return deviceSensorTemperatureList
                .stream()
                .filter(DeviceSensorTemperature::isTimeOnRange)
                .findFirst();
    }


}
