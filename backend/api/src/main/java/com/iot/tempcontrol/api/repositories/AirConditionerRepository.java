package com.iot.tempcontrol.api.repositories;

import com.iot.tempcontrol.api.domains.AirConditioner;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirConditionerRepository extends MongoRepository<AirConditioner, String> {
}
