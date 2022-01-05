package com.example.weatherapp.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class TimeUtil {
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

    public static String generateISO() {
        LocalDateTime yesterday = LocalDate.now().atStartOfDay().minusDays(1);
        return yesterday.toString() + ":00Z";
    }
    
}
