package com.example.weatherapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditRequestDto {
    private Long id;
    private String username;
    private String password;
    private String email;
}
