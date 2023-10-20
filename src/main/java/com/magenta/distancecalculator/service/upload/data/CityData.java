package com.magenta.distancecalculator.service.upload.data;

public class CityData {

    // Класс для чтения городов в XML-файле

    private String name;

    private Double latitude;

    private Double longitude;

    public CityData() {
    }

    public CityData(String name, Double latitude, Double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
