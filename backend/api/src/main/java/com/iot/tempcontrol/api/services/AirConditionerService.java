package com.iot.tempcontrol.api.services;

import com.iot.tempcontrol.api.domains.AirConditioner;
import com.iot.tempcontrol.api.repositories.AirConditionerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirConditionerService {
    @Autowired
    private AirConditionerRepository airConditionerRepository;

    public List<AirConditioner> getAll() {
        return airConditionerRepository.findAll();
    }

    public Optional<AirConditioner> getById(String id) {
        return airConditionerRepository.findById(id);
    }

    public AirConditioner create(AirConditioner airConditioner) {
        return airConditionerRepository.insert(airConditioner);
    }

    public AirConditioner update(AirConditioner airConditioner) {
        return airConditionerRepository.save(airConditioner);
    }

    public void delete(AirConditioner airConditioner) {
        airConditionerRepository.delete(airConditioner);
    }
}
