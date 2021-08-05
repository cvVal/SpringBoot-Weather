package com.detroitlabs.weatherapp.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WeatherClientConfig {

    @Bean(name = "weatherRestTemplate")
    public RestTemplate weatherRestTemplate() {
        return new RestTemplate();
    }
}
