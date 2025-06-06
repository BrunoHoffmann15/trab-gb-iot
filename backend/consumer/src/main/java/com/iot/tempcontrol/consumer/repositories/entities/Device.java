package com.iot.tempcontrol.consumer.repositories.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Document("devices")
public class Device {
    @Id
    public String id;

    @Indexed(unique = true)
    public String referencedDevice;

    public String idAirConditioner;
}
