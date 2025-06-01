package com.iot.tempcontrol.consumer.repositories;

import com.iot.tempcontrol.consumer.repositories.entities.AirConditioner;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AirConditionerRepository extends MongoRepository<AirConditioner, String> {
}
