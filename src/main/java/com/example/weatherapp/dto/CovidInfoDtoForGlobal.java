package com.example.weatherapp.dto;

import lombok.Getter;
import org.json.JSONObject;

@Getter
public class CovidInfoDtoForGlobal {

    private long newConfirmed;
    private long totalConfirmed;
    private long newDeaths;
    private long totalDeath;
    private long newRecovered;
    private long totalRecovered;
    private String date;

    public CovidInfoDtoForGlobal(JSONObject jsonObject) {
        this.newConfirmed = jsonObject.getLong("NewConfirmed");
        this.totalConfirmed = jsonObject.getLong("TotalConfirmed");
        this.newDeaths = jsonObject.getLong("NewDeaths");
        this.totalDeath = jsonObject.getLong("TotalDeaths");
        this.newRecovered = jsonObject.getLong("NewRecovered");
        this.totalRecovered = jsonObject.getLong("TotalRecovered");
        this.date = jsonObject.getString("Date");
    }
}
