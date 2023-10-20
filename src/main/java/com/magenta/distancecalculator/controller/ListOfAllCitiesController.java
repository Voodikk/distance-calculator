package com.magenta.distancecalculator.controller;

import com.magenta.distancecalculator.model.City;
import com.magenta.distancecalculator.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/distance-calculator")
public class ListOfAllCitiesController {

    // Контроллер списка городов

    private final CityRepository repository;

    @Autowired
    public ListOfAllCitiesController(CityRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cities")
    public Map<Long, String> getCities() {
        List<City> cities = repository.findAll();

        return cities.stream()
                .collect(Collectors.toMap(City::getId, City::getName));
    }
}
