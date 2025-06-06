package com.iot.tempcontrol.consumer.repositories;

import com.iot.tempcontrol.consumer.repositories.entities.Temperature;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemperatureRepository extends MongoRepository<Temperature, String> {
    List<Temperature> findAllByIdDevice(String idDevice);
}
