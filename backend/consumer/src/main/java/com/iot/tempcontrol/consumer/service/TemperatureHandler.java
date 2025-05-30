package com.iot.tempcontrol.consumer.service;

import com.iot.tempcontrol.consumer.domain.AirConditioner;
import com.iot.tempcontrol.consumer.domain.DeviceSensorTemperature;
import com.iot.tempcontrol.consumer.repositories.TemperatureRepository;
import com.iot.tempcontrol.consumer.repositories.adapters.TemperatureAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TemperatureHandler {

    @Autowired
    public TemperatureRepository temperatureRepository;

    public void handle(DeviceSensorTemperature temperature) {
        saveTemperature(temperature);
    }

    public void saveTemperature(DeviceSensorTemperature deviceSensorTemperature) {
        var temperature = TemperatureAdapter.convertToTemperature(deviceSensorTemperature);
        temperatureRepository.save(temperature);
    }

    private void handleTemperatureChange() {
        AirConditioner airConditioner = new AirConditioner();

        if (airConditioner.isAllDevicesWithUpdatedTemperature()) {
            var actionToBeDone = airConditioner.getActionToBeDone();
        }
    }
}
