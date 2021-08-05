package com.detroitlabs.weatherapp.model.common;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Main {

    private Double temp;
    private Integer pressure;
    private Integer humidity;

    @JsonAlias("feels_like")
    private Double feelsLike;

    @JsonAlias("temp_min")
    private Double tempMin;

    @JsonAlias("temp_max")
    private Double tempMax;

    @JsonAlias("sea_level")
    private Integer seaLevel;

    @JsonAlias("grnd_level")
    private Integer grndLevel;

    @JsonAlias("temp_kf")
    private Integer tempKf;
}
