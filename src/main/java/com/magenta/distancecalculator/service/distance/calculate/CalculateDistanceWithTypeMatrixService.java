package com.magenta.distancecalculator.service.distance.calculate;

import com.magenta.distancecalculator.model.City;
import com.magenta.distancecalculator.model.Distance;
import com.magenta.distancecalculator.repository.DistanceRepository;
import com.magenta.distancecalculator.service.distance.interfaces.Calculability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CalculateDistanceWithTypeMatrixService implements Calculability {

    private final DistanceRepository repository;

    @Autowired
    public CalculateDistanceWithTypeMatrixService(DistanceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Map<String, Double> calculate(List<City> fromCityList, List<City> toCityList) {

        Map<String, Double> map = new HashMap<>();

        for (City fromCity : fromCityList) {
            for (City toCity : toCityList) {
                if (fromCity.equals(toCity)) {
                    continue;
                }
                else {
                    Optional<Distance> temp = repository.findDistanceByFromCityAndToCity(fromCity, toCity);

                    if (temp.isPresent()) {
                        Distance distance = temp.get();
                        map.put("Distance Matrix: Расстояние от города " + fromCity.getName() + " до города " + toCity.getName(), distance.getDistance());
                    }
                    else
                        map.put("Distance Matrix: В базе данных отсутствуют данные о дистанции между городами " + fromCity.getName() + " и " + toCity.getName(), null);
                }
            }
        }
        return map;
    }
}
