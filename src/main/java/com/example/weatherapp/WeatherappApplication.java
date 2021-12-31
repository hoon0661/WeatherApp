package com.example.weatherapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WeatherappApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherappApplication.class, args);
    }

}
