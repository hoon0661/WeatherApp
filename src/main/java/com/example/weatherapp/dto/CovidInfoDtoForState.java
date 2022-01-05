package com.example.weatherapp.dto;

import lombok.Getter;
import org.json.JSONObject;

@Getter
public class CovidInfoDtoForState {

    private String province;
    private long confirmed;
    private long deaths;
    private long recovered;
    private long active;
    private String date;

    public CovidInfoDtoForState(JSONObject covidJson) {
        this.province = covidJson.getString("Province");
        this.confirmed = covidJson.getLong("Confirmed");
        this.deaths = covidJson.getLong("Deaths");
        this.recovered = covidJson.getLong("Recovered");
        this.active = covidJson.getLong("Active");
        this.date = covidJson.getString("Date");
    }
}
