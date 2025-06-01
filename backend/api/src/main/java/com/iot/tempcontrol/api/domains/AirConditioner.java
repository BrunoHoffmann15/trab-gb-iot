package com.iot.tempcontrol.api.domains;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("air-conditioners")
public class AirConditioner {
    public String id;
    public TemperatureRange temperatureRange;
    public Location location;
}
