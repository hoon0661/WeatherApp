package com.example.weatherapp.utils;

import com.example.weatherapp.config.Config;
import com.example.weatherapp.dto.WeatherDtoForFiveDays;
import com.example.weatherapp.dto.WeatherDtoForOneDay;
import com.example.weatherapp.exception.ApiRequestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class OpenWeatherSearch {
    public String searchForOneDay(String query, String unit) {
        if (query == null || query.trim().length() != 5) {
            throw new ApiRequestException("Please type 5-digit zipcode (ex: 10118).");
        }
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity;
        responseEntity = rest.exchange("http://api.openweathermap.org/data/2.5/weather?zip=" + query + "&units=" + unit + "&appid=" + Config.OPEN_WEATHER_KEY, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        System.out.println(status);
        String response = responseEntity.getBody();
        return response;
    }

    public String searchForFiveDays(String query, String unit) {
        if (query == null || query.trim().length() != 5) {
            throw new ApiRequestException("Please type 5-digit zipcode (ex: 10118).");
        }
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String body = "";
        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("http://api.openweathermap.org/data/2.5/forecast?zip=" + query + "&units=" + unit + "&appid=" + Config.OPEN_WEATHER_KEY, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        return response;
    }

    public WeatherDtoForOneDay fromJSONtoWeatherForOneDay(String result) {
        JSONObject rjson = new JSONObject(result);
        WeatherDtoForOneDay weatherDto = new WeatherDtoForOneDay(rjson);
        return weatherDto;
    }

    public List<WeatherDtoForFiveDays> fromJSONtoWeatherForFiveDays(String result) {
        List<WeatherDtoForFiveDays> list = new ArrayList<>();
        JSONObject rjson = new JSONObject(result);
        String name = rjson.getJSONObject("city").getString("name");
        JSONArray array = rjson.getJSONArray("list");
        for (int i = 0; i < array.length(); i += 8) {
            JSONObject object = (JSONObject) array.get(i);
            WeatherDtoForFiveDays weatherDtoForFiveDays = new WeatherDtoForFiveDays(name, object);
            list.add(weatherDtoForFiveDays);
        }
        return list;
    }
}
