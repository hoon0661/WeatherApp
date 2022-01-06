package com.example.weatherapp.controllers;

import com.example.weatherapp.dto.*;
import com.example.weatherapp.utils.CovidDataSearch;
import com.example.weatherapp.utils.OpenWeatherSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SearchRequestController {

    private final OpenWeatherSearch openWeatherSearch;
    private final CovidDataSearch covidDataSearch;

    @GetMapping("/api/search/oneday")
    public WeatherDtoForOneDay getWeatherForOneDay(@RequestParam String query, @RequestParam String unit) {
        String response = openWeatherSearch.searchForOneDay(query, unit);
        return openWeatherSearch.fromJSONtoWeatherForOneDay(response);
    }

    @GetMapping("/api/search/fivedays")
    public List<WeatherDtoForFiveDays> getWeatherForFiveDays(@RequestParam String query, @RequestParam String unit) {
        String response = openWeatherSearch.searchForFiveDays(query, unit);
        return openWeatherSearch.fromJSONtoWeatherForFiveDays(response);
    }

    @GetMapping("/api/search/covid/states")
    public CovidInfoDtoForState getCovidInfoTodayForState(@RequestParam String query) {
        String response = covidDataSearch.getCovidDataForState();
        return covidDataSearch.fromJSONtoCovidInfoForState(response, query);
    }

    @GetMapping("/api/search/covid/national")
    public CovidInfoDtoForNational getCovidInfoForNational() {
        String response = covidDataSearch.getCovidDataForGlobal();
        return covidDataSearch.fromJSONtoCovidInfoForNational(response);
    }

    @GetMapping("/api/search/covid/global")
    public CovidInfoDtoForGlobal getCovidInfoForGlobal() {
        String response = covidDataSearch.getCovidDataForGlobal();
        return covidDataSearch.fromJSONtoCovidInfoForGlobal(response);
    }
}
