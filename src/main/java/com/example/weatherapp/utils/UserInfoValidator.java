package com.example.weatherapp.utils;

import com.example.weatherapp.exception.ApiRequestException;

import java.util.regex.Pattern;

public class UserInfoValidator {
    public static void validateUsername(String username) {
        if (username == null) {
            throw new ApiRequestException("Please type username");
        }
        if (username.trim().length() < 3) {
            throw new ApiRequestException("Username must be longer than 2 letters.");
        }
        if (!Character.isLetter(username.charAt(0))) {
            throw new ApiRequestException("Username must start with a letter.");
        }

        for (int i = 1; i < username.length(); i++) {
            char letter = username.charAt(i);
            if (!Character.isLetterOrDigit(letter)) {
                throw new ApiRequestException("Username can contain only digit and letter.");
            }
        }
    }

    public static void validateEmail(String email) {
        if (email == null || email.trim().length() == 0) {
            throw new ApiRequestException("Please type email.");
        }

        String pattern = "^(.+)@(\\S+)$";
        if (!Pattern.compile(pattern).matcher(email).matches()) {
            throw new ApiRequestException("Please check the format of your email.");
        }
    }

    public static void validatePassword(String password) {
        if (password == null || password.trim().length() == 0) {
            throw new ApiRequestException("Please type password");
        }

        if (password.length() < 4) {
            throw new ApiRequestException("Password must be longer than 4 characters");
        }
    }
}
