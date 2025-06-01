package com.iot.tempcontrol.consumer.service;

import com.iot.tempcontrol.consumer.domain.AirConditioner;
import com.iot.tempcontrol.consumer.domain.DeviceSensorTemperature;
import com.iot.tempcontrol.consumer.repositories.AirConditionerRepository;
import com.iot.tempcontrol.consumer.repositories.DeviceRepository;
import com.iot.tempcontrol.consumer.repositories.TemperatureRepository;
import com.iot.tempcontrol.consumer.repositories.adapters.AirConditionerAdapter;
import com.iot.tempcontrol.consumer.repositories.adapters.TemperatureAdapter;
import com.iot.tempcontrol.consumer.domain.Device;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TemperatureHandler {

    @Autowired
    public TemperatureRepository temperatureRepository;

    @Autowired
    public DeviceRepository deviceRepository;

    @Autowired
    public AirConditionerRepository airConditionerRepository;

    @Autowired
    public ProducerHandler producerHandler;

    public void handle(DeviceSensorTemperature temperature) {
        saveTemperature(temperature);
    }

    public void saveTemperature(DeviceSensorTemperature deviceSensorTemperature) {
        var temperature = TemperatureAdapter.convertToTemperature(deviceSensorTemperature);
        temperatureRepository.save(temperature);
    }

    private void handleTemperatureChange(DeviceSensorTemperature deviceSensorTemperature) {
        var device = deviceRepository.findById(deviceSensorTemperature.idDevice);

        if (device.isEmpty())
            return;

        var devices = getAllDevicesInfo(device.get().idAirConditioner);
        var airConditioner = getAirConditioner(device.get().idAirConditioner, devices);

        if (airConditioner.isAllDevicesWithUpdatedTemperature()) {
            var actionToBeDone = airConditioner.getActionToBeDone();
        }
    }

    private List<Device> getAllDevicesInfo(String idAirConditioner) {
        var allDevices = deviceRepository.findAllByIdAirConditioner(idAirConditioner);
        return allDevices.stream().map(d -> {
            var temperaturesE = temperatureRepository.findAllByIdDevice(d.idDevice);
            var temperaturesD = temperaturesE
                    .stream()
                    .map(TemperatureAdapter::convertToDeviceSensorTemperature)
                    .collect(Collectors.toList());

            return new Device(temperaturesD);
        }).collect(Collectors.toList());
    }

    private AirConditioner getAirConditioner(String idAirConditioner, List<Device> devices) {
        var airConditioner = airConditionerRepository.findById(idAirConditioner).get();
        return AirConditionerAdapter.convertFromEntityToDomain(airConditioner, devices);
    }
}
