# WeatherApp

This project is to learn & utilize Spring Security as well as frontend technologies including HTML5, CSS, Javascript to
develop a fullstack application with responsive UI using Bootstrap.

## Technology Used

- Java
- Spring Web
- MySQL
- JPA
- Lombok
- Spring Seciruty
- HTML5
- CSS
- Bootstrap5
- Javascript
- JQuery
- Thymeleaf
- AWS RDS
- AWS EC2
- Gradle
- IntelliJ

## Features

- User can sign up/login and edit user info.
- A couple of third-party APIs have been used in the backend to provide weather information and COVID-19 data.
- User can view today's weather info, 5 days forecast
- User can view COVID-19 data in US state level, US national level and global level.
- this web app has a responsive UI using Bootstrap.
- This server has been deployed using AWS RDS and EC2.
- APIs used: Weather data(https://openweathermap.org/api), COVID-19
  data(https://documenter.getpostman.com/view/10808728/SzS8rjbc).
- WeatherApp Address: http://weatherapp.hoonkim.link/

## Updates

- Added scheduler to reset public username and password (username: username, password: password) everyhour.
- Added exception handler to handle error when fetching data from APIs.
- Added loading spinner to display while fetching data.

## API Reference

- Please use this as a prefix for all API endpoints

```http
  http://weatherapp.hoonkim.link/api/search
```

- To test API using API platforms such as Postman or ARC, users must go through athentication before testing all the
  APIs below. The way I found is to request POST at /user/login with request body where username and password as keys,
  and put corresponding user's username and password as values.

#### Get Today's weather info

```http
  GET /oneday?query={US zipcode}&unit={unit}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `query`      | `String` | **Required**. 5 digits US Zipcode. |
| `unit`      | `String` | **
Required**. Temperature unit. can be either "metric" for Celsius or "imperial" for Fahrenheit |

#### Get 5 days forecast

```http
  GET /fivedays?query={US zipcode}&unit={unit}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `query`      | `String` | **Required**. 5 digits US Zipcode. |
| `unit`      | `String` | **
Required**. Temperature unit. can be either "metric" for Celsius or "imperial" for Fahrenheit |

#### Get a live case in US state level

```http
  GET /covid/states?query={zipcode}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `query`      | `String` | **Required**. 5 digits US Zipcode. |

#### Get a live case in US national level

```http
  GET /covid/national
```

#### Get a live case in global level

```http
  GET /covid/global
```

## Input Validation

#### All inputs must be not null nor empty. If any input doesn't meet its validation, IllegalArgumentException is thrown with its corresponding message.

| Parameter | Type     | validation                       |
| :-------- | :------- | :-------------------------------- |
| `username`      | `String` | not less than 3 letters, first character must be a letter, only contains alphabet letters and digits |
| `email`      | `String` | must include @ |
| `password`      | `String` | not less than 4 characters |

## To Improve

- Handle Exceptions coming from user input validation on client side

## Lesson Learned

- Requests to endpoints can be configured for auth using WebsecurityConfigurerAdabter, and it can even configure
  security for others such as html, css, and even h2 database.
- For password, BcryptPasswordEncoder is used, and it is for one-way encryption. In other words, it can encode raw
  password but cannot decode it.
- @Controller and Thymeleaf are used to provide dynamic web pages.
- Exception handling was not easy for me (blame on my coding skills :(....) on this project especially when validating
  user input and handling exceptions from server side on client side. Thymeleaf is used but it was a bit complicated
  step for me.
- Making all requests in REST and using a tool such as ajax (instead of returning multiple HTML pages with errors) makes
  more sense to me and seems easier for exception handling.
- Returning multiple HTML pages (using @Controller and Thymeleaf) seems too redundant. Maybe creating a SPA using a
  framework such as REACT could be a solution.
- Bootstrap5 was magic to me. I didn't have to write much css code to create UI. Initially the UI got broken in smaller
  screensize but media query was really helpful to solve the issue.

