package com.iot.tempcontrol.consumer.repositories.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "temperatures")
public class Temperature {

    @Id
    public String id;

    public String referencedDevice;

    public String idDevice;

    public float temperature;

    public LocalDateTime createdAt;

    public Temperature(String idDevice, String referencedDevice, float temperature, LocalDateTime createdAt) {
        this.createdAt = createdAt;
        this.temperature = temperature;
        this.idDevice = idDevice;
        this.referencedDevice = referencedDevice;
    }
}
