package com.example.weatherapp.utils;

import com.example.weatherapp.dto.CovidInfoDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CovidDataSearch {

    public String getCovidData() {
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

    public CovidInfoDto fromJSONtoCovidInfo(String result, String zipcode) {
        JSONArray array = new JSONArray(result);
        String state = ZipcodeStateMapper.getState(zipcode);
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            if (obj.getString("Province").equals(state)) {
                CovidInfoDto covidInfoDto = new CovidInfoDto(obj);
                return covidInfoDto;
            }
        }
        return null;
    }
}
