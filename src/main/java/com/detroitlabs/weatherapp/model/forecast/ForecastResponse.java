package com.detroitlabs.weatherapp.model.forecast;

import com.detroitlabs.weatherapp.model.common.ErrorResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastResponse extends ErrorResponse {

    private Integer cnt;
    private List<ForeCastList> list;
    private City city;
}
