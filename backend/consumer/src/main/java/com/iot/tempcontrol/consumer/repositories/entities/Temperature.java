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
    public String idDevice;

    public float temperature;

    public LocalDateTime createdAt;
}
