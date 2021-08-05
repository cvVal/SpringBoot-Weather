package com.detroitlabs.weatherapp.controller;

import com.detroitlabs.weatherapp.model.common.ErrorResponse;
import com.detroitlabs.weatherapp.model.forecast.ForecastResponse;
import com.detroitlabs.weatherapp.model.weather.WeatherResponse;
import com.detroitlabs.weatherapp.service.IWeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/weather-app",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class WeatherController {

    private final IWeatherService iWeatherService;

    @GetMapping("/weather-current-location")
    @ApiOperation(
            value = "GET",
            notes = "Weather endpoint to fetch the current temperature based on user location",
            tags = "weather-current-location",
            response = WeatherResponse.class
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = WeatherResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 429, message = "Too Many Requests", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
    })
    private ResponseEntity<WeatherResponse> getWeatherAtCurrentLocation(@RequestParam String latitude,
                                                                        @RequestParam String longitude) throws JsonProcessingException {

        WeatherResponse response = iWeatherService.getCurrentWeather(latitude, longitude);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("/weather-current-zip")
    private ResponseEntity<WeatherResponse> getWeatherByZipcode(@RequestParam String zipCode) throws JsonProcessingException {

        WeatherResponse response = iWeatherService.getCurrentWeatherByZipCode(zipCode);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("/forecast-current-location")
    @ApiOperation(
            value = "GET",
            notes = "Forecast endpoint to fetch 5 day and 3 hourly temperature based on user location",
            tags = "forecast-current-location",
            response = WeatherResponse.class
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = ForecastResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 429, message = "Too Many Requests", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
    })
    private ResponseEntity<ForecastResponse> getForecastAtCurrentLocation(@RequestParam String latitude,
                                                                          @RequestParam String longitude) throws JsonProcessingException {

        ForecastResponse response = iWeatherService.getCurrentForecast(latitude, longitude);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
