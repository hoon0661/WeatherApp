package com.example.weatherapp.utils;

import com.example.weatherapp.config.Config;
import com.example.weatherapp.dto.WeatherDto;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class OpenWeatherSearch {
    public String searchForOneDay(String query, String unit) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("http://api.openweathermap.org/data/2.5/weather?zip=" + query + "&units=" + unit + "&appid=" + Config.OPEN_WEATHER_KEY, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        return response;
    }

    public WeatherDto fromJSONtoWeatherForOneDay(String result) {
        JSONObject rjson = new JSONObject(result);
        WeatherDto weatherDto = new WeatherDto(rjson);
        return weatherDto;
    }
}
