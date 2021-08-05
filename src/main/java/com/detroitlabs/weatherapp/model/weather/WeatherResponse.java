package com.detroitlabs.weatherapp.model.weather;

import com.detroitlabs.weatherapp.model.common.CommonWeatherResponse;
import com.detroitlabs.weatherapp.model.common.Coord;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse extends CommonWeatherResponse {

    private Coord coord;
    private String base;
    private Sys sys;
    private Long timezone;
    private Long id;
    private String name;
}

