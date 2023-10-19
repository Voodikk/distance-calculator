package com.magenta.distancecalculator.repository;

import com.magenta.distancecalculator.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    @Override
    List<City> findAll();

    Optional<City> findCityById(Long id);

    Optional<City> findCityByName(String name);
}
