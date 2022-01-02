package com.example.weatherapp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeConverter {
    public static String covertUnixToHour(int time) {
        Date date = new Date(time * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String formatted = sdf.format(date);
        return formatted;
    }

    public static String covertUnixToDate(int time) {
        Date date = new Date(time * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-YYYY");
        String formatted = sdf.format(date);
        return formatted;
    }
}
