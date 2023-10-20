package com.magenta.distancecalculator.service.distance;

import com.magenta.distancecalculator.model.City;
import com.magenta.distancecalculator.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FindCityByNameService {

    private final CityRepository rep;

    @Autowired
    public FindCityByNameService(CityRepository rep) {
        this.rep = rep;
    }

    // Метод преобразования списка названий городов в список городов
    public List<City> find(List<String> namesCity) throws Exception {
        List<City> cities = new ArrayList<>();
        for (String name : namesCity) {
            Optional<City> temp = rep.findCityByName(name);

            if (temp.isPresent()) {
                City city = temp.get();
                cities.add(city);
            }
            else throw new Exception("В предоставленном списке городов, есть хотя бы один город, который отсутствует в таблице \"city\"");
        }
        return cities;
    }
}
