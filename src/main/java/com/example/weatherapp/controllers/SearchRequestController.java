package com.example.weatherapp.controllers;

import com.example.weatherapp.dto.*;
import com.example.weatherapp.exception.ApiException;
import com.example.weatherapp.utils.CovidDataSearch;
import com.example.weatherapp.utils.OpenWeatherSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
//        long start = System.currentTimeMillis();
        String response = openWeatherSearch.searchForOneDay(query, unit);
        WeatherDtoForOneDay weatherDtoForOneDay = openWeatherSearch.fromJSONtoWeatherForOneDay(response);
//        long end = System.currentTimeMillis();
//        System.out.println("one day: " + (end - start) + "ms");
        return weatherDtoForOneDay;
    }

    @GetMapping("/api/search/fivedays")
    public List<WeatherDtoForFiveDays> getWeatherForFiveDays(@RequestParam String query, @RequestParam String unit) {
//        long start = System.currentTimeMillis();
        String response = openWeatherSearch.searchForFiveDays(query, unit);
        List<WeatherDtoForFiveDays> list = openWeatherSearch.fromJSONtoWeatherForFiveDays(response);
//        long end = System.currentTimeMillis();
//        System.out.println("five days: " + (end - start) + "ms");
        return list;
    }

    @GetMapping("/api/search/covid/states")
    public CovidInfoDtoForState getCovidInfoTodayForState(@RequestParam String query) {
//        long start = System.currentTimeMillis();
        String response = covidDataSearch.getCovidDataForState();
        CovidInfoDtoForState covidInfoDtoForState = covidDataSearch.fromJSONtoCovidInfoForState(response, query);
//        long end = System.currentTimeMillis();
//        System.out.println("Covid state: " + (end - start) + "ms");
        return covidInfoDtoForState;
    }

    @GetMapping("/api/search/covid/national")
    public CovidInfoDtoForNational getCovidInfoForNational() {
//        long start = System.currentTimeMillis();
        String response = covidDataSearch.getCovidDataForGlobal();
        CovidInfoDtoForNational covidInfoDtoForNational = covidDataSearch.fromJSONtoCovidInfoForNational(response);
//        long end = System.currentTimeMillis();
//        System.out.println("Covid national: " + (end - start) + "ms");
        return covidInfoDtoForNational;
    }

    @GetMapping("/api/search/covid/global")
    public CovidInfoDtoForGlobal getCovidInfoForGlobal() {
//        long start = System.currentTimeMillis();
        String response = covidDataSearch.getCovidDataForGlobal();
        CovidInfoDtoForGlobal covidInfoDtoForGlobal = covidDataSearch.fromJSONtoCovidInfoForGlobal(response);
//        long end = System.currentTimeMillis();
//        System.out.println("Covid global: " + (end - start) + "ms");
        return covidInfoDtoForGlobal;
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Object> handle(IllegalArgumentException ex) {
        ApiException apiException = new ApiException(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(
                apiException,
                HttpStatus.BAD_REQUEST
        );
    }
}