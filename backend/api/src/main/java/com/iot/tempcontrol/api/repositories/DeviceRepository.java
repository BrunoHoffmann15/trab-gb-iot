package com.iot.tempcontrol.api.repositories;

import com.iot.tempcontrol.api.domains.Device;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends MongoRepository<Device, String> {
}
