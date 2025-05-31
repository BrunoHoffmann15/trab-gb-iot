package com.iot.tempcontrol.api.models;

import com.iot.tempcontrol.api.domains.AirConditioner;
import com.iot.tempcontrol.api.domains.Location;
import com.iot.tempcontrol.api.domains.TemperatureRange;

public class AirConditionerCreateRequest {
    public Location location;
    public TemperatureRange temperatureRange;

    public AirConditioner convert() {
        var airCondition = new AirConditioner();

        airCondition.location = location;
        airCondition.temperatureRange = this.temperatureRange;

        return airCondition;
    }
}

