package com.iot.tempcontrol.consumer.domain;

import java.util.List;
import java.util.Optional;

public class AirConditioner {
    public List<Device> devices;
    public TemperatureRange temperatureRange;

    public AirConditioner(List<Device> devices, TemperatureRange temperatureRange) {
        this.devices = devices;
        this.temperatureRange = temperatureRange;
    }

    public boolean isAllDevicesWithUpdatedTemperature() {
        return devices.stream()
                .map(Device::getLastTemperature)
                .allMatch(Optional::isPresent);
    }

    public Action getActionToBeDone() {
        float meanTemperature = devices
                .stream()
                .map(Device::getLastTemperature)
                .map(d -> d.get().temperature)
                .reduce(0f, Float::sum);

        if (meanTemperature >= temperatureRange.maxTemperature)
            return Action.DECREASE;
        else if (meanTemperature <= temperatureRange.minTemperature)
            return Action.INCREASE;

        return Action.KEEP;
    }
}
