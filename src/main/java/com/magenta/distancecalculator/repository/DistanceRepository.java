package com.magenta.distancecalculator.repository;

import com.magenta.distancecalculator.model.City;
import com.magenta.distancecalculator.model.Distance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistanceRepository extends JpaRepository<Distance, Long> {

    // Репозиторий сущности Distance

    @Override
    List<Distance> findAll();

    Optional<Distance> findDistanceById(Long id);

    Optional<Distance> findDistanceByFromCityAndToCity(City fromCity, City toCity);
}
