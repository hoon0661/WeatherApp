package com.example.weatherapp.dto;

import com.example.weatherapp.utils.TimeUtil;
import lombok.Getter;
import org.json.JSONObject;

@Getter
public class WeatherDtoForOneDay {
    private String name;
    private String main;
    private String icon;
    private double temp;
    private double feelsLike;
    private double tempMin;
    private double tempMax;
    private int pressure;
    private int humidity;
    private double windSpeed;
    private int windDeg;
    private String datetime;
    private String sunrise;
    private String sunset;

    public WeatherDtoForOneDay(JSONObject weatherJson) {
        this.name = weatherJson.getString("name");
        this.main = weatherJson.getJSONArray("weather").getJSONObject(0).getString("main");
        this.icon = weatherJson.getJSONArray("weather").getJSONObject(0).getString("icon");
        this.temp = weatherJson.getJSONObject("main").getDouble("temp");
        this.feelsLike = weatherJson.getJSONObject("main").getDouble("feels_like");
        this.tempMin = weatherJson.getJSONObject("main").getDouble("temp_min");
        this.tempMax = weatherJson.getJSONObject("main").getDouble("temp_max");
        this.pressure = weatherJson.getJSONObject("main").getInt("pressure");
        this.humidity = weatherJson.getJSONObject("main").getInt("humidity");
        this.windSpeed = weatherJson.getJSONObject("wind").getDouble("speed");
        this.windDeg = weatherJson.getJSONObject("wind").getInt("deg");
        this.datetime = TimeUtil.covertUnixToHour(weatherJson.getInt("dt"));
        this.sunrise = TimeUtil.covertUnixToHour(weatherJson.getJSONObject("sys").getInt("sunrise"));
        this.sunset = TimeUtil.covertUnixToHour(weatherJson.getJSONObject("sys").getInt("sunset"));
    }
}

