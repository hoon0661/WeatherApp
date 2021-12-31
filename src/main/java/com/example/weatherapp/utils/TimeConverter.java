package com.example.weatherapp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeConverter {
    public static String covertUnixToHour(int sunrise) {
        Date date = new Date(sunrise * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String formatted = sdf.format(date);
        return formatted;
    }
}
