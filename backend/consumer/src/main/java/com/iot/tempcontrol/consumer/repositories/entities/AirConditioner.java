package com.iot.tempcontrol.consumer.repositories.entities;

import com.iot.tempcontrol.consumer.domain.TemperatureRange;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("air-conditioners")
public class AirConditioner {
    @Id
    public String id;

    @Indexed(unique = true)
    public String referencedAirConditioner;

    public TemperatureRange temperatureRange;
}
