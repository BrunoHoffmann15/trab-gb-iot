package com.iot.tempcontrol.consumer.repositories.adapters;

import com.iot.tempcontrol.consumer.domain.AirConditioner;
import com.iot.tempcontrol.consumer.domain.Device;

import java.util.List;

public class AirConditionerAdapter {
    public static AirConditioner convertFromEntityToDomain(com.iot.tempcontrol.consumer.repositories.entities.AirConditioner airConditioner,
                                                    List<Device> deviceList) {
        return new AirConditioner(deviceList, airConditioner.temperatureRange);
    }
}
