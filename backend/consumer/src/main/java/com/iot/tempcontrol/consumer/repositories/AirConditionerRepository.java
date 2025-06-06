package com.iot.tempcontrol.consumer.repositories;

import com.iot.tempcontrol.consumer.repositories.entities.AirConditioner;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirConditionerRepository extends MongoRepository<AirConditioner, String> {
}
