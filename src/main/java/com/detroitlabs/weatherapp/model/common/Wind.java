package com.detroitlabs.weatherapp.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Wind {

    private Double speed;
    private Integer deg;
    private Double gust;
}
