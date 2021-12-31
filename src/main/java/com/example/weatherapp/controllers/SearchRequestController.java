package com.example.weatherapp.controllers;

import com.example.weatherapp.dto.WeatherDto;
import com.example.weatherapp.utils.OpenWeatherSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SearchRequestController {

    private final OpenWeatherSearch openWeatherSearch;

    @GetMapping("/api/search/oneday")
    public WeatherDto getWeatherForOneDay(@RequestParam String query, @RequestParam String unit) {
        String response = openWeatherSearch.searchForOneDay(query, unit);
        return openWeatherSearch.fromJSONtoWeatherForOneDay(response);
    }
}
