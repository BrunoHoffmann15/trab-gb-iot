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

    public void create(AirConditioner airConditioner) {
        airConditionerRepository.insert(airConditioner);
    }
}
