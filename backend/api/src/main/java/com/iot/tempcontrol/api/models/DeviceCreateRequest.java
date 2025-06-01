package com.iot.tempcontrol.api.models;

import com.iot.tempcontrol.api.domains.Device;
import com.iot.tempcontrol.api.domains.Location;

public class DeviceCreateRequest {
    public String idAirConditioner;
    public Location location;

    public Device convert() {
        var device = new Device();

        device.location = location;
        device.idAirConditioner = idAirConditioner;

        return device;
    }
}
