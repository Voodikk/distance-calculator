package com.magenta.distancecalculator.service.upload.data;


public class DistanceData {

    // Класс для чтения дистанций в XML-файле

    private Long idFromCity;

    private Long idToCity;

    private Double distance;

    public DistanceData() {
    }

    public DistanceData(Long idFromCity, Long idToCity, Double distance) {
        this.idFromCity = idFromCity;
        this.idToCity = idToCity;
        this.distance = distance;
    }

    public Long getIdFromCity() {
        return idFromCity;
    }

    public void setIdFromCity(Long idFromCity) {
        this.idFromCity = idFromCity;
    }

    public Long getIdToCity() {
        return idToCity;
    }

    public void setIdToCity(Long idToCity) {
        this.idToCity = idToCity;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
