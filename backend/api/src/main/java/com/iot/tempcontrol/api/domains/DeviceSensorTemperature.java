package com.iot.tempcontrol.api.domains;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "temperatures")
public class DeviceSensorTemperature {
    @Id
    public String id;

    public String idDevice;

    public LocalDateTime createdAt;

    public float temperature;
}
