package com.example.weatherapp.controllers;

import com.example.weatherapp.dto.WeatherDtoForFiveDays;
import com.example.weatherapp.dto.WeatherDtoForOneDay;
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
}
