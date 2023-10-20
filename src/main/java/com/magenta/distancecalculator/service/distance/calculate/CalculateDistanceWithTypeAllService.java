package com.magenta.distancecalculator.service.distance.calculate;

import com.magenta.distancecalculator.model.City;
import com.magenta.distancecalculator.service.distance.interfaces.Calculability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CalculateDistanceWithTypeAllService implements Calculability {

    private final CalculateDistanceWithTypeCrowFlightService serviceCrowflight;

    private final CalculateDistanceWithTypeMatrixService serviceMatrix;

    @Autowired
    public CalculateDistanceWithTypeAllService(CalculateDistanceWithTypeCrowFlightService serviceCrowflight, CalculateDistanceWithTypeMatrixService serviceMatrix) {
        this.serviceCrowflight = serviceCrowflight;
        this.serviceMatrix = serviceMatrix;
    }

    // Метод рассчитывания дистанции всеми способами
    @Override
    public Map<String, Double> calculate(List<City> fromCity, List<City> toCity) {

        Map<String, Double> map = new HashMap<>();
        Map<String, Double> mapMatrix = serviceMatrix.calculate(fromCity, toCity);
        Map<String, Double> mapCrowflight = serviceCrowflight.calculate(fromCity, toCity);

        map.putAll(mapMatrix);
        map.putAll(mapCrowflight);

        return map;
    }
}
