package com.detroitlabs.weatherapp.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonWeatherResponse extends ErrorResponse {

    private Long dt;
    private Integer visibility;
    private Main main;
    private List<Weather> weather;
    private Clouds clouds;
    private Wind wind;
}
