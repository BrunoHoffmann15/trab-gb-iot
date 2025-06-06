package com.iot.tempcontrol.api.models;

import com.iot.tempcontrol.api.domains.AirConditioner;
import com.iot.tempcontrol.api.domains.Location;
import com.iot.tempcontrol.api.domains.TemperatureRange;

public class AirConditionerUpdateRequest {
    public String referencedAirConditioner;
    public Location location;
    public TemperatureRange temperatureRange;

    public AirConditioner convert(String id) {
        var airConditioner = new AirConditioner();

        airConditioner.id = id;
        airConditioner.referencedAirConditioner = referencedAirConditioner;
        airConditioner.location = location;
        airConditioner.temperatureRange = temperatureRange;

        return airConditioner;
    }
}
