package com.iot.tempcontrol.api.services;

import com.iot.tempcontrol.api.domains.Device;
import com.iot.tempcontrol.api.domains.DeviceSensorTemperature;
import com.iot.tempcontrol.api.repositories.DeviceRepository;
import com.iot.tempcontrol.api.repositories.DeviceSensorTemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceSensorTemperatureRepository deviceSensorTemperatureRepository;

    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    public Optional<Device> findById(String id) {
        return deviceRepository.findById(id);
    }

    public Device save(Device device) {
        return deviceRepository.save(device);
    }

    public void delete(String id) {
        deviceRepository.deleteById(id);
    }

    public List<DeviceSensorTemperature> getAllTemperatures(String idDevice) {
        return deviceSensorTemperatureRepository.findByIdDevice(idDevice);
    }

    public DeviceSensorTemperature createNewTemperature(DeviceSensorTemperature deviceSensorTemperature) {
        return deviceSensorTemperatureRepository.save(deviceSensorTemperature);
    }
}