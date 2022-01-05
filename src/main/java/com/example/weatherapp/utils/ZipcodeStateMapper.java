package com.example.weatherapp.utils;

public class ZipcodeStateMapper {
    public static String getState(String zipcodeStr) {
        int zipcode = Integer.parseInt(zipcodeStr);
        String state;

        if (zipcode >= 35000 && zipcode <= 36999) {
            state = "Alabama";
        } else if (zipcode >= 99500 && zipcode <= 99999) {
            state = "Alaska";
        } else if (zipcode >= 85000 && zipcode <= 86999) {
            state = "Arizona";
        } else if (zipcode >= 71600 && zipcode <= 72999) {
            state = "Arkansas";
        } else if (zipcode >= 90000 && zipcode <= 96699) {
            state = "California";
        } else if (zipcode >= 80000 && zipcode <= 81999) {
            state = "Colorado";
        } else if ((zipcode >= 6000 && zipcode <= 6389) || (zipcode >= 6391 && zipcode <= 6999)) {
            state = "Connecticut";
        } else if (zipcode >= 19700 && zipcode <= 19999) {
            state = "Delaware";
        } else if (zipcode >= 32000 && zipcode <= 34999) {
            state = "Florida";
        } else if ((zipcode >= 30000 && zipcode <= 31999) || (zipcode >= 39800 && zipcode <= 39999)) {
            state = "Georgia";
        } else if (zipcode >= 96700 && zipcode <= 96999) {
            state = "Hawaii";
        } else if (zipcode >= 83200 && zipcode <= 83999) {
            state = "Idaho";
        } else if (zipcode >= 60000 && zipcode <= 62999) {
            state = "Illinois";
        } else if (zipcode >= 46000 && zipcode <= 47999) {
            state = "Indiana";
        } else if (zipcode >= 50000 && zipcode <= 52999) {
            state = "Iowa";
        } else if (zipcode >= 66000 && zipcode <= 67999) {
            state = "Kansas";
        } else if (zipcode >= 40000 && zipcode <= 42999) {
            state = "Kentucky";
        } else if (zipcode >= 70000 && zipcode <= 71599) {
            state = "Louisiana";
        } else if (zipcode >= 3900 && zipcode <= 4999) {
            state = "Maine";
        } else if (zipcode >= 20600 && zipcode <= 21999) {
            state = "Maryland";
        } else if ((zipcode >= 1000 && zipcode <= 2799) || (zipcode == 5501) || (zipcode == 5544)) {
            state = "Massachusetts";
        } else if (zipcode >= 48000 && zipcode <= 49999) {
            state = "Michigan";
        } else if (zipcode >= 55000 && zipcode <= 56899) {
            state = "Minnesota";
        } else if (zipcode >= 38600 && zipcode <= 39999) {
            state = "Mississippi";
        } else if (zipcode >= 63000 && zipcode <= 65999) {
            state = "Missouri";
        } else if (zipcode >= 59000 && zipcode <= 59999) {
            state = "Montana";
        } else if (zipcode >= 27000 && zipcode <= 28999) {
            state = "North Carolina";
        } else if (zipcode >= 58000 && zipcode <= 58999) {
            state = "North Dakota";
        } else if (zipcode >= 68000 && zipcode <= 69999) {
            state = "Nebraska";
        } else if (zipcode >= 88900 && zipcode <= 89999) {
            state = "Nevada";
        } else if (zipcode >= 3000 && zipcode <= 3899) {
            state = "New Hampshire";
        } else if (zipcode >= 7000 && zipcode <= 8999) {
            state = "New Jersey";
        } else if (zipcode >= 87000 && zipcode <= 88499) {
            state = "New Mexico";
        } else if ((zipcode >= 10000 && zipcode <= 14999) || (zipcode == 6390) || (zipcode == 501) || (zipcode == 544)) {
            state = "New York";
        } else if (zipcode >= 43000 && zipcode <= 45999) {
            state = "Ohio";
        } else if ((zipcode >= 73000 && zipcode <= 73199) || (zipcode >= 73400 && zipcode <= 74999)) {
            state = "Oklahoma";
        } else if (zipcode >= 97000 && zipcode <= 97999) {
            state = "Oregon";
        } else if (zipcode >= 15000 && zipcode <= 19699) {
            state = "Pennsylvania";
        } else if (zipcode >= 300 && zipcode <= 999) {
            state = "Puerto Rico";
        } else if (zipcode >= 2800 && zipcode <= 2999) {
            state = "Rhode Island";
        } else if (zipcode >= 29000 && zipcode <= 29999) {
            state = "South Carolina";
        } else if (zipcode >= 57000 && zipcode <= 57999) {
            state = "South Dakota";
        } else if (zipcode >= 37000 && zipcode <= 38599) {
            state = "Tennessee";
        } else if ((zipcode >= 75000 && zipcode <= 79999) || (zipcode >= 73301 && zipcode <= 73399) || (zipcode >= 88500 && zipcode <= 88599)) {
            state = "Texas";
        } else if (zipcode >= 84000 && zipcode <= 84999) {
            state = "Utah";
        } else if (zipcode >= 5000 && zipcode <= 5999) {
            state = "Vermont";
        } else if ((zipcode >= 20100 && zipcode <= 20199) || (zipcode >= 22000 && zipcode <= 24699) || (zipcode == 20598)) {
            state = "Virginia";
        } else if ((zipcode >= 20000 && zipcode <= 20099) || (zipcode >= 20200 && zipcode <= 20599) || (zipcode >= 56900 && zipcode <= 56999)) {
            state = "Washington DC";
        } else if (zipcode >= 98000 && zipcode <= 99499) {
            state = "Washington";
        } else if (zipcode >= 24700 && zipcode <= 26999) {
            state = "West Virginia";
        } else if (zipcode >= 53000 && zipcode <= 54999) {
            state = "Wisconsin";
        } else if (zipcode >= 82000 && zipcode <= 83199) {
            state = "Wyoming";
        } else {
            state = "none";
        }

        return state;
    }

}
