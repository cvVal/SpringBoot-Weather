package com.detroitlabs.weatherapp.model.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorResponse {

    private String cod;
    private HttpStatus status;
    private Object message;
}
