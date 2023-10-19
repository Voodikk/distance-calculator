package com.magenta.distancecalculator.service.distance.interfaces;

import com.magenta.distancecalculator.model.City;

import java.util.List;
import java.util.Map;

public interface Calculability {
    Map<String, Double> calculate(List<City> fromCity, List<City> toCity);
}
