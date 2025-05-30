package com.iot.tempcontrol.consumer.repositories;

import com.iot.tempcontrol.consumer.repositories.entities.Temperature;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TemperatureRepository extends MongoRepository<Temperature, String> {

}
