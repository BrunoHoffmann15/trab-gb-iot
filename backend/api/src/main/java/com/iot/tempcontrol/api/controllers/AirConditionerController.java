package com.iot.tempcontrol.api.controllers;

import com.iot.tempcontrol.api.models.AirConditionerCreateRequest;
import com.iot.tempcontrol.api.models.AirConditionerUpdateRequest;
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
    public ResponseEntity<?> create(@RequestBody AirConditionerCreateRequest request) {
        var airConditioner = request.convert();
        var response = airConditionerService.create(airConditioner);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody AirConditionerUpdateRequest request) {
        var airConditioner = request.convert(id);

        if (airConditionerService.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(airConditionerService.update(airConditioner));
    }

}
