package com.detroitlabs.weatherapp.service;

import com.detroitlabs.weatherapp.client.WeatherClient;
import com.detroitlabs.weatherapp.model.forecast.ForecastResponse;
import com.detroitlabs.weatherapp.model.weather.WeatherResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IWeatherServiceImpl implements IWeatherService {

    private final WeatherClient weatherClient;

    @Override
    public WeatherResponse getCurrentWeather(String latitude, String longitude) throws JsonProcessingException {
        return weatherClient.getCurrentWeather(latitude, longitude);
    }

    @Override
    public WeatherResponse getCurrentWeatherByZipCode(String zipCode) throws JsonProcessingException {
        return weatherClient.getCurrentWeatherByZipCode(zipCode);
    }

    @Override
    public ForecastResponse getCurrentForecast(String latitude, String longitude) throws JsonProcessingException {
        return weatherClient.getCurrentForecast(latitude, longitude);
    }
}
