package com.iot.tempcontrol.api.domains;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("devices")
public class Device {
    @Id
    public String id;

    @Indexed(unique = true)
    public String referencedDevice;

    public Location location;

    public String idAirConditioner;
}
