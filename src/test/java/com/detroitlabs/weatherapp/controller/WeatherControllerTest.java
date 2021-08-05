package com.detroitlabs.weatherapp.controller;

import com.detroitlabs.weatherapp.model.weather.WeatherResponse;
import com.detroitlabs.weatherapp.service.IWeatherServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherController.class)
@AutoConfigureMockMvc
class WeatherControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    IWeatherServiceImpl weatherService;

    @Test
    public void weatherControllerTest() throws Exception {
        var weatherResponse = new WeatherResponse();
        weatherResponse.setStatus(HttpStatus.OK);

        given(weatherService.getCurrentWeather(anyString(), anyString())).willReturn(weatherResponse);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/v1/weather-app/weather-current-location?latitude=42&longitude=-83")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();

        assertNotNull(response);
    }
}