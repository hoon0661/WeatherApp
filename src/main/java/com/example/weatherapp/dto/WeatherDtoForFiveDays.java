package com.example.weatherapp.dto;

import lombok.Getter;
import org.json.JSONObject;

@Getter
public class WeatherDtoForFiveDays {
    private String name;
    private String icon;
    private double temp;
    private double feelsLike;
    private double tempMin;
    private double tempMax;
    private double windSpeed;


    public WeatherDtoForFiveDays(String name, JSONObject weatherJson) {
        this.name = name;
        this.icon = weatherJson.getJSONArray("weather").getJSONObject(0).getString("icon");
        this.temp = weatherJson.getJSONObject("main").getDouble("temp");
        this.feelsLike = weatherJson.getJSONObject("main").getDouble("feels_like");
        this.tempMin = weatherJson.getJSONObject("main").getDouble("temp_min");
        this.tempMax = weatherJson.getJSONObject("main").getDouble("temp_max");
        this.windSpeed = weatherJson.getJSONObject("wind").getDouble("speed");
    }

}

