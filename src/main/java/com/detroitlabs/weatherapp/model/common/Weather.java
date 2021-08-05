package com.detroitlabs.weatherapp.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Weather {

    private Integer id;
    private String main;
    private String description;
    private String icon;
}
