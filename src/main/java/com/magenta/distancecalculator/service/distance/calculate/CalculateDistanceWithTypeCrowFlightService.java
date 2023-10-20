package com.magenta.distancecalculator.service.distance.calculate;

import com.magenta.distancecalculator.model.City;
import com.magenta.distancecalculator.service.distance.interfaces.Calculability;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CalculateDistanceWithTypeCrowFlightService implements Calculability {

    private static final int RADIUS_OF_EARTH = 6371;


    // Метод рассчитывания дистанции способом Crowflight
    @Override
    public Map<String, Double> calculate(List<City> fromCityList, List<City> toCityList) {

        Map<String, Double> map = new HashMap<>();

        for (City fromCity : fromCityList) {
            for (City toCity : toCityList) {

                // Если города одинаковые, пропускаем итерацию (метод equals() переопределён в сущности City)
                if (fromCity.equals(toCity)) {
                    continue;
                }
                else {
                    // Решение по формуле нахождения расстояния между двумя точками на сфере

                    double latDistance = Math.toRadians(toCity.getLatitude() - fromCity.getLatitude());
                    double lonDistance = Math.toRadians(toCity.getLongitude() - fromCity.getLongitude());

                    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                            + Math.cos(Math.toRadians(fromCity.getLatitude())) * Math.cos(Math.toRadians(toCity.getLatitude()))
                            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

                    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

                    double distance = RADIUS_OF_EARTH * c;

                    map.put("Crowflight: Расстояние от города " + fromCity.getName() + " до города " + toCity.getName(), distance);
                }
            }
        }
        return map;
    }
}
