package com.magenta.distancecalculator.service.distance;

import com.magenta.distancecalculator.model.City;
import com.magenta.distancecalculator.service.distance.calculate.CalculateDistanceWithTypeAllService;
import com.magenta.distancecalculator.service.distance.calculate.CalculateDistanceWithTypeCrowFlightService;
import com.magenta.distancecalculator.service.distance.calculate.CalculateDistanceWithTypeMatrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckTypeOfCalculateService {

    // Класс проверки типа расчёта

    private final FindCityByNameService findCityByNameService;

    private final CalculateDistanceWithTypeMatrixService calculateDistanceWithTypeMatrixService;

    private final CalculateDistanceWithTypeCrowFlightService calculateDistanceWithTypeCrowFlightService;

    private final CalculateDistanceWithTypeAllService calculateDistanceWithTypeAllService;

    // Метод проверки
    @Autowired
    public CheckTypeOfCalculateService(FindCityByNameService findCityByNameService, CalculateDistanceWithTypeMatrixService calculateDistanceWithTypeMatrixService, CalculateDistanceWithTypeCrowFlightService calculateDistanceWithTypeCrowFlightService, CalculateDistanceWithTypeAllService calculateDistanceWithTypeAllService) {
        this.findCityByNameService = findCityByNameService;
        this.calculateDistanceWithTypeMatrixService = calculateDistanceWithTypeMatrixService;
        this.calculateDistanceWithTypeCrowFlightService = calculateDistanceWithTypeCrowFlightService;
        this.calculateDistanceWithTypeAllService = calculateDistanceWithTypeAllService;
    }

    public ResponseEntity<?> check(String calculateType,
                                   List<String> fromCities,
                                   List<String> toCities) {

        // Преобразовываем список названий городов в список городов
        List<City> fromCityList;
        List<City> toCityList;
        try {
            fromCityList = findCityByNameService.find(fromCities);
            toCityList = findCityByNameService.find(toCities);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        // Проверка на тип вычисления
        if (calculateType.equalsIgnoreCase("Crowflight")) {
            return ResponseEntity.ok(calculateDistanceWithTypeCrowFlightService.calculate(fromCityList, toCityList));
        }
        else if (calculateType.equalsIgnoreCase("Distance Matrix")) {
            return ResponseEntity.ok(calculateDistanceWithTypeMatrixService.calculate(fromCityList, toCityList));
        }
        else if (calculateType.equalsIgnoreCase("All")) {
            return ResponseEntity.ok(calculateDistanceWithTypeAllService.calculate(fromCityList, toCityList));
        }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка: Введён неправильный тип расчёта дистанции, внимательно ознакомьтесь с инструкцией");
    }
}
