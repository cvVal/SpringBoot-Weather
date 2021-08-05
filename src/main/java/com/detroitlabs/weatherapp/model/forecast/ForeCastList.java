package com.detroitlabs.weatherapp.model.forecast;

import com.detroitlabs.weatherapp.model.common.CommonWeatherResponse;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ForeCastList extends CommonWeatherResponse {

    private Sys sys;

    @JsonAlias("dt_txt")
    private String dtTxt;
}
