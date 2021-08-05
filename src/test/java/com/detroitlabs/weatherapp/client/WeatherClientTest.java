package com.detroitlabs.weatherapp.client;

import com.detroitlabs.weatherapp.model.common.Main;
import com.detroitlabs.weatherapp.model.weather.WeatherResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherClientTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    WeatherClient weatherClient;

    @BeforeEach
    public void setup() {
        weatherClient = new WeatherClient(restTemplate);
        ReflectionTestUtils.setField(weatherClient, "weatherUrl", "https://weather{lat}{lon}.com");
        ReflectionTestUtils.setField(weatherClient, "apiKey", "7T2c1");
    }

    @Test
    void getCurrentWeather() throws JsonProcessingException {

        var main = new Main();
        main.setTemp(76.5);
        var weatherResponse = new WeatherResponse();
        weatherResponse.setCod("200");
        weatherResponse.setMain(main);

        ResponseEntity<WeatherResponse> weatherResponseEntity = new ResponseEntity<>(weatherResponse, HttpStatus.OK);

        when(restTemplate.getForEntity(any(URI.class), eq(WeatherResponse.class))).thenReturn(weatherResponseEntity);

        WeatherResponse response = weatherClient.getCurrentWeather("42", "80");

        assertNotNull(response);
        assertEquals("200", response.getCod());
        assertEquals(76.5, response.getMain().getTemp());
    }

    @Test
    void getCurrentForecast() {
    }
}