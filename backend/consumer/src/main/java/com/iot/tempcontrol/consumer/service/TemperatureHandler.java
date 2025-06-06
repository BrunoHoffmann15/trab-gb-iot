package com.iot.tempcontrol.consumer.service;

import com.iot.tempcontrol.consumer.domain.Action;
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
import java.util.Map;
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
        handleTemperatureChange(temperature);
    }

    public void saveTemperature(DeviceSensorTemperature deviceSensorTemperature) {
        var deviceE = deviceRepository.findByReferencedDevice(deviceSensorTemperature.referencedDevice);
        var temperature = TemperatureAdapter.convertToTemperature(deviceSensorTemperature);

        if (deviceE.isEmpty()) {
            log.error(String.format("Device %s not found.", deviceSensorTemperature.referencedDevice));
            throw new RuntimeException("Device not found");
        }

        temperature.idDevice = deviceE.get().id;
        temperature.referencedDevice = deviceE.get().referencedDevice;

        temperatureRepository.save(temperature);
    }

    private void handleTemperatureChange(DeviceSensorTemperature deviceSensorTemperature) {
        var device = deviceRepository.findByReferencedDevice(deviceSensorTemperature.referencedDevice);

        if (device.isEmpty()) {
            log.error(String.format("Device %s not found.", deviceSensorTemperature.idDevice));
            throw new RuntimeException("Device not found");
        }

        var devices = getAllDevicesInfo(device.get().idAirConditioner);
        var airConditioner = getAirConditioner(device.get().idAirConditioner, devices);

        if (airConditioner.isAllDevicesWithUpdatedTemperature()) {
            var actionToBeDone = airConditioner.getActionToBeDone();

            Map<String, String> message = Map.of("action", actionToBeDone.toString(),
                                                    "idAirConditioner", airConditioner.referencedAirConditioner);

            producerHandler.handle(message, String.format("/air_conditioning/%s", airConditioner.referencedAirConditioner));
        }
    }

    private List<Device> getAllDevicesInfo(String idAirConditioner) {
        var allDevices = deviceRepository.findAllByIdAirConditioner(idAirConditioner);

        return allDevices.stream().map(d -> {
            var temperaturesE = temperatureRepository.findAllByIdDevice(d.id);
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
