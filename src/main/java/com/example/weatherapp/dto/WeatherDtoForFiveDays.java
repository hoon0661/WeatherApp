package com.example.weatherapp.dto;

import com.example.weatherapp.utils.TimeUtil;
import lombok.Getter;
import org.json.JSONObject;

@Getter
public class WeatherDtoForFiveDays {
    private String name;
    private String main;
    private String icon;
    private double temp;
    private double feelsLike;
    private double tempMin;
    private double tempMax;
    private double windSpeed;
    private double humidity;
    private String datetime;


    public WeatherDtoForFiveDays(String name, JSONObject weatherJson) {
        this.name = name;
        this.main = weatherJson.getJSONArray("weather").getJSONObject(0).getString("main");
        this.icon = weatherJson.getJSONArray("weather").getJSONObject(0).getString("icon");
        this.temp = weatherJson.getJSONObject("main").getDouble("temp");
        this.feelsLike = weatherJson.getJSONObject("main").getDouble("feels_like");
        this.tempMin = weatherJson.getJSONObject("main").getDouble("temp_min");
        this.tempMax = weatherJson.getJSONObject("main").getDouble("temp_max");
        this.humidity = weatherJson.getJSONObject("main").getDouble("humidity");
        this.windSpeed = weatherJson.getJSONObject("wind").getDouble("speed");
        this.datetime = TimeUtil.covertUnixToDate(weatherJson.getInt("dt"));
    }

}

