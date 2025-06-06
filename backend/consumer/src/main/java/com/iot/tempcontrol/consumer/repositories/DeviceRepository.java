package com.iot.tempcontrol.consumer.repositories;

import com.iot.tempcontrol.consumer.repositories.entities.Device;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends MongoRepository<Device, String> {
    List<Device> findAllByIdAirConditioner(String idAirConditioner);
    Optional<Device> findByReferencedDevice(String referencedDevice);
}
