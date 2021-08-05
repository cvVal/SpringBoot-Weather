package com.detroitlabs.weatherapp.client;

import com.detroitlabs.weatherapp.model.forecast.ForecastResponse;
import com.detroitlabs.weatherapp.model.weather.WeatherResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@Component
public class WeatherClient {

    private final RestTemplate restTemplate;

    public WeatherClient(@Qualifier("weatherRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${weather-client.weather-url}")
    private String weatherUrl;

    @Value("${weather-client.weather-zip-url}")
    private String weatherZipUrl;

    @Value("${weather-client.forecast-url}")
    private String forecastUrl;

    @Value("${weather-client.client-api-key}")
    private String apiKey;

    /**
     * @param latitude is used to set latitude coordinate on user's current position
     * @param longitude is used to set longitude coordinate on user's current position
     * @return This method will return either a success or a failed WeatherResponse response from the client call
     * @throws JsonProcessingException
     */
    public WeatherResponse getCurrentWeather(String latitude, String longitude) throws JsonProcessingException {
        var uri = getUri(latitude, longitude, weatherUrl);

        try {
            ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(uri, WeatherResponse.class);
            Objects.requireNonNull(response.getBody()).setStatus(HttpStatus.OK);

            return response.getBody();
        } catch (HttpClientErrorException e) {

            var error = e.getResponseBodyAsString();
            var objectMapper = new ObjectMapper();
            var errorResponse = objectMapper.readValue(error, WeatherResponse.class);
            errorResponse.setStatus(e.getStatusCode());

            return errorResponse;
        }
    }

    /**
     * @param zipCode is used to set current user's zipcode location
     * @return This method will return either a success or a failed WeatherResponse response from the client call
     * @throws JsonProcessingException
     */
    public WeatherResponse getCurrentWeatherByZipCode(String zipCode) throws JsonProcessingException {
        var uri = UriComponentsBuilder.fromUriString(weatherZipUrl).buildAndExpand(zipCode, apiKey).toUri();

        try {
            ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(uri, WeatherResponse.class);
            Objects.requireNonNull(response.getBody()).setStatus(HttpStatus.OK);

            return response.getBody();
        } catch (HttpClientErrorException e) {

            var error = e.getResponseBodyAsString();
            var objectMapper = new ObjectMapper();
            var errorResponse = objectMapper.readValue(error, WeatherResponse.class);
            errorResponse.setStatus(e.getStatusCode());

            return errorResponse;
        }
    }

    /**
     * @param latitude is used to set latitude coordinate with respect to user's current position
     * @param longitude is used to set longitude coordinate with respect to user's current position
     * @return This method will return either a success or a failed ForecastResponse response from the client call
     * @throws JsonProcessingException
     */
    public ForecastResponse getCurrentForecast(String latitude, String longitude) throws JsonProcessingException {
        var uri = getUri(latitude, longitude, forecastUrl);

        try {
            ResponseEntity<ForecastResponse> response = restTemplate.getForEntity(uri, ForecastResponse.class);
            Objects.requireNonNull(response.getBody()).setStatus(HttpStatus.OK);

            return response.getBody();
        } catch (HttpClientErrorException e) {
            var error = e.getResponseBodyAsString();
            var objectMapper = new ObjectMapper();
            var errorResponse = objectMapper.readValue(error, ForecastResponse.class);
            errorResponse.setStatus(e.getStatusCode());

            return errorResponse;
        }
    }

    /**
     * @param latitude is used to set latitude coordinate with respect to user's current position
     * @param longitude is used to set longitude coordinate with respect to user's current position
     * @param url is used to set the client's service url
     * @return This method will return a Uniform Resource Identifier (URI) reference based on @param values
     */
    private URI getUri(String latitude, String longitude, String url) {
        return UriComponentsBuilder.fromUriString(url).buildAndExpand(latitude, longitude, apiKey).toUri();
    }
}
