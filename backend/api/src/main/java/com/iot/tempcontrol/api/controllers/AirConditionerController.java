package com.iot.tempcontrol.api.controllers;

import com.iot.tempcontrol.api.models.AirConditionerCreateRequest;
import com.iot.tempcontrol.api.services.AirConditionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/air-conditioners")
public class AirConditionerController {
    @Autowired
    private AirConditionerService airConditionerService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        var airConditioner = airConditionerService.getById(id);

        if (airConditioner.isPresent())
            return ResponseEntity.ok(airConditioner.get());

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        var airConditioners = airConditionerService.getAll();
        return ResponseEntity.ok(airConditioners);
    }

    @PostMapping
    public void create(@RequestBody AirConditionerCreateRequest request) {
        var airConditioner = request.convert();
        airConditionerService.create(airConditioner);
    }

}
