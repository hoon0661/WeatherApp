package com.example.weatherapp.dto;

import lombok.Getter;
import org.json.JSONObject;

@Getter
public class WeatherDto {
    private String name;
    private String icon;
    private double temp;
    private double feelsLike;
    private double tempMin;
    private double tempMax;
    private int pressure;
    private int humidity;
    private double windSpeed;
    private int windDeg;

    public WeatherDto(JSONObject weatherJson) {
        this.name = weatherJson.getString("name");
        this.icon = weatherJson.getJSONArray("weather").getJSONObject(0).getString("icon");
        this.temp = weatherJson.getJSONObject("main").getDouble("temp");
        this.feelsLike = weatherJson.getJSONObject("main").getDouble("feels_like");
        this.tempMin = weatherJson.getJSONObject("main").getDouble("temp_min");
        this.tempMax = weatherJson.getJSONObject("main").getDouble("temp_max");
        this.pressure = weatherJson.getJSONObject("main").getInt("pressure");
        this.humidity = weatherJson.getJSONObject("main").getInt("humidity");
        this.windSpeed = weatherJson.getJSONObject("wind").getDouble("speed");
        this.windDeg = weatherJson.getJSONObject("wind").getInt("deg");

    }
}

