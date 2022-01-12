package com.example.weatherapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(Exception ex) {
        ApiException apiException = new ApiException(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(
                apiException,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = {HttpClientErrorException.class})
    public ResponseEntity<Object> handleApiRequestExceptionForWeatherAndCovidApi(HttpClientErrorException ex) {
        ApiException apiException = new ApiException(
//                ex.getMessage(),
                "(404) Zipcode is not Valid.",
                ex.getStatusCode()

        );

        return new ResponseEntity<>(
                apiException,
                ex.getStatusCode()
        );
    }
}
