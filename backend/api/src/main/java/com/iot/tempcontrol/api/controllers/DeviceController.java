package com.iot.tempcontrol.api.controllers;

import com.iot.tempcontrol.api.domains.Device;
import com.iot.tempcontrol.api.models.DeviceCreateRequest;
import com.iot.tempcontrol.api.models.DeviceSensorTemperatureCreateRequest;
import com.iot.tempcontrol.api.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public List<Device> getAllDevices() {
        return deviceService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDeviceById(@PathVariable String id) {
        var device = deviceService.findById(id);

        if (device.isPresent())
            return ResponseEntity.ok(device.get());

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createDevice(@RequestBody DeviceCreateRequest request) {
        var device = request.convert();
        var response = deviceService.save(device);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable String id) {
        deviceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/temperatures")
    public ResponseEntity<?> getDeviceTemperature(@PathVariable String id) {
        var device = deviceService.findById(id);

        if (device.isEmpty())
            return ResponseEntity.notFound().build();

        var temperatures = deviceService.getAllTemperatures(id);

        return ResponseEntity.ok(temperatures);
    }

    @PostMapping("/{id}/temperatures")
    public ResponseEntity<?> createTemperature(@PathVariable String id, @RequestBody DeviceSensorTemperatureCreateRequest request) {
        var device = deviceService.findById(id);

        if (device.isEmpty())
            return ResponseEntity.notFound().build();

        var temperature = request.convert(id);
        var response = deviceService.createNewTemperature(temperature);

        return ResponseEntity.ok(response);
    }
}

