package com.magenta.distancecalculator.controller;

import com.magenta.distancecalculator.service.distance.CheckTypeOfCalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/distance-calculator")
public class CalculateDistanceController {

    private final CheckTypeOfCalculateService service;

    @Autowired
    public CalculateDistanceController(CheckTypeOfCalculateService service) {
        this.service = service;
    }

    @GetMapping("/calculate")
    public ResponseEntity<?> getResult(
            @RequestParam("calculationType") String calculationType,
            @RequestParam("fromCityList") List<String> fromCities,
            @RequestParam("toCityList") List<String> toCities) {

        return service.check(calculationType, fromCities, toCities);
    }
}
