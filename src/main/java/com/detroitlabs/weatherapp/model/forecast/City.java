package com.detroitlabs.weatherapp.model.forecast;

import com.detroitlabs.weatherapp.model.common.Coord;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class City {

    private Long id;
    private String name;
    private Coord coord;
    private String country;
    private Long timezone;
    private Long sunrise;
    private Long sunset;
}
