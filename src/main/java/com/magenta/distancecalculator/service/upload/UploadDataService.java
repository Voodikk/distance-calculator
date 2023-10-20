package com.magenta.distancecalculator.service.upload;

import com.magenta.distancecalculator.model.City;
import com.magenta.distancecalculator.model.Distance;
import com.magenta.distancecalculator.repository.CityRepository;
import com.magenta.distancecalculator.repository.DistanceRepository;
import com.magenta.distancecalculator.service.upload.data.CityData;
import com.magenta.distancecalculator.service.upload.data.Data;
import com.magenta.distancecalculator.service.upload.data.DistanceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
public class UploadDataService {

    // Класс загрузки данных из файла в базу данных

    private final CityRepository cityRepository;

    private final DistanceRepository distanceRepository;

    @Autowired
    public UploadDataService(CityRepository cityRepository, DistanceRepository distanceRepository) {
        this.cityRepository = cityRepository;
        this.distanceRepository = distanceRepository;
    }


    public ResponseEntity<?> uploadDataToDB(MultipartFile file) {

        try {
            // Чтение XML файла
            Data data = readFile(file);

            // Сохранение городов из файла в базу данных
            for (CityData cityData : data.getCities()) {
                City city = parseToCityEntity(cityData);
                if (cityRepository.findCityByName(city.getName()).isEmpty())
                    cityRepository.save(city);
                else throw new Exception("Ошибка: Город с названием " + city.getName() + " уже есть в базе данных");
            }

            // Сохранение дистанций из файла в базу данных
            for (DistanceData distanceData : data.getDistances()) {
                Distance distance = parseToDistanceEntity(distanceData);
                distanceRepository.save(distance);
            }

            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (IOException | JAXBException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка чтения файла, внимательно ознакомьтесь с инструкцией");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Метод чтения файла
    private Data readFile(MultipartFile file) throws JAXBException, IOException {

        JAXBContext context = JAXBContext.newInstance(Data.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        InputStream stream = file.getInputStream();
        return (Data) unmarshaller.unmarshal(stream);
    }

    // Метод преобразования класса для чтения xml-файла(CityData) в сущность City
    private City parseToCityEntity(CityData cityData) {
        City city = new City();
        city.setName(cityData.getName());
        city.setLatitude(cityData.getLatitude());
        city.setLongitude(cityData.getLongitude());

        return city;
    }
    // Метод преобразования класса для чтения xml-файла(DistanceData) в сущность Distance
    private Distance parseToDistanceEntity(DistanceData distanceData) throws Exception {
        Optional<City> temp1 = cityRepository.findCityById(distanceData.getIdFromCity());
        Optional<City> temp2 = cityRepository.findCityById(distanceData.getIdToCity());
        City fromCity;
        City toCity;

        if (temp1.isPresent() || temp2.isPresent()) {
            fromCity = temp1.get();
            toCity = temp2.get();
        }
        else throw new Exception("Вы указываете дистанцию между городами, где хотя бы один город отсутствует в базе данных \"city\" ");

        Distance distance = new Distance();
        distance.setFromCity(fromCity);
        distance.setToCity(toCity);
        distance.setDistance(distanceData.getDistance());

        return distance;
    }
}
