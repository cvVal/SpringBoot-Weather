package com.detroitlabs.weatherapp.service;

import com.detroitlabs.weatherapp.model.forecast.ForecastResponse;
import com.detroitlabs.weatherapp.model.weather.WeatherResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IWeatherService {

    WeatherResponse getCurrentWeather(String latitude, String longitude) throws JsonProcessingException;
    WeatherResponse getCurrentWeatherByZipCode(String zipCode) throws JsonProcessingException;
    ForecastResponse getCurrentForecast(String latitude, String longitude) throws JsonProcessingException;
}
