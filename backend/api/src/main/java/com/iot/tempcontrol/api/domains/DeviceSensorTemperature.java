package com.iot.tempcontrol.api.domains;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;

import java.time.LocalDateTime;

@Collation("temperatures")
public class DeviceSensorTemperature {
    @Id
    public String id;

    public String idDevice;

    public LocalDateTime createdAt;

    public float temperature;
}
