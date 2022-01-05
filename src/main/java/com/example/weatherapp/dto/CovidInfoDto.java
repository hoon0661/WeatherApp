package com.example.weatherapp.dto;

import lombok.Getter;
import org.json.JSONObject;

@Getter
public class CovidInfoDto {

    private String province;
    private long confirmed;
    private long deaths;
    private long recovered;
    private long active;
    private String date;

    public CovidInfoDto(JSONObject covidJson) {
        this.province = covidJson.getString("Province");
        this.confirmed = (long) covidJson.getInt("Confirmed");
        this.deaths = (long) covidJson.getInt("Deaths");
        this.recovered = (long) covidJson.getInt("Recovered");
        this.active = (long) covidJson.getInt("Active");
        this.date = covidJson.getString("Date");
    }
}
