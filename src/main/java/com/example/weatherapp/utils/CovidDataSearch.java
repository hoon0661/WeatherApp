package com.example.weatherapp.utils;

import com.example.weatherapp.dto.CovidInfoDtoForGlobal;
import com.example.weatherapp.dto.CovidInfoDtoForNational;
import com.example.weatherapp.dto.CovidInfoDtoForState;
import com.example.weatherapp.exception.ApiRequestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CovidDataSearch {

    public String getCovidDataForState() {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String body = "";
        String yesterdayTime = TimeUtil.generateISOForYesterday();
        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://api.covid19api.com/live/country/united-states/status/confirmed/date/" + yesterdayTime, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        return response;
    }

    public CovidInfoDtoForState fromJSONtoCovidInfoForState(String result, String query) {
        if (query == null || query.trim().length() != 5) {
            throw new ApiRequestException("Please type 5-digit zipcode (ex: 10118).");
        }
        JSONArray array = new JSONArray(result);
        String state = ZipcodeStateMapper.getState(query);
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            if (obj.getString("Province").equals(state)) {
                CovidInfoDtoForState covidInfoDto = new CovidInfoDtoForState(obj);
                return covidInfoDto;
            }
        }
        return null;
    }

    public String getCovidDataForGlobal() {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://api.covid19api.com/summary", HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        return response;
    }

    public CovidInfoDtoForNational fromJSONtoCovidInfoForNational(String result) {
        JSONObject object = new JSONObject(result);
        String state = "United States of America";
        JSONArray array = object.getJSONArray("Countries");
        for (int i = 0; i < array.length(); i++) {
            JSONObject country = array.getJSONObject(i);
            if (country.getString("Country").equals(state)) {
                return new CovidInfoDtoForNational(country);
            }
        }
        return null;
    }

    public CovidInfoDtoForGlobal fromJSONtoCovidInfoForGlobal(String result) {
        JSONObject object = new JSONObject(result);
        return new CovidInfoDtoForGlobal(object.getJSONObject("Global"));
    }
}
