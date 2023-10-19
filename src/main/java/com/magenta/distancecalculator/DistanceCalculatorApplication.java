package com.magenta.distancecalculator;

import liquibase.Liquibase;
import liquibase.database.core.MySQLDatabase;
import liquibase.resource.ResourceAccessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;

@SpringBootApplication
public class DistanceCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistanceCalculatorApplication.class, args);
    }

}
