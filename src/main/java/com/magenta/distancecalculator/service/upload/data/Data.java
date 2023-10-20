package com.magenta.distancecalculator.service.upload.data;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {

    // Класс для чтения XML-файла

    @XmlElementWrapper(name = "Cities")
    @XmlElement(name = "City")
    private List<CityData> cities = new ArrayList<>();

    @XmlElementWrapper(name = "Distances")
    @XmlElement(name = "Distance")
    private List<DistanceData> distances = new ArrayList<>();

    public Data() {
    }

    public Data(List<CityData> cities, List<DistanceData> distances) {
        this.cities = cities;
        this.distances = distances;
    }

    public List<CityData> getCities() {
        return cities;
    }

    public void setCities(List<CityData> cities) {
        this.cities = cities;
    }

    public List<DistanceData> getDistances() {
        return distances;
    }

    public void setDistances(List<DistanceData> distances) {
        this.distances = distances;
    }
}
