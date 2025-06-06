package com.iot.tempcontrol.api.models;

import com.iot.tempcontrol.api.domains.Device;
import com.iot.tempcontrol.api.domains.Location;

public class DeviceCreateRequest {
    public String referencedDevice;
    public String idAirConditioner;
    public Location location;

    public Device convert() {
        var device = new Device();

        device.referencedDevice = this.referencedDevice;
        device.location = this.location;
        device.idAirConditioner = this.idAirConditioner;

        return device;
    }
}
