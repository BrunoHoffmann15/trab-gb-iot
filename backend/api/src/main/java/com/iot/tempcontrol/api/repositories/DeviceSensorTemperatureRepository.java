package com.iot.tempcontrol.api.repositories;

import com.iot.tempcontrol.api.domains.DeviceSensorTemperature;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceSensorTemperatureRepository extends MongoRepository<DeviceSensorTemperature, String> {
    List<DeviceSensorTemperature> findByIdDevice(String idDevice);
}
