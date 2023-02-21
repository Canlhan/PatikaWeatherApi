package com.can.PatikaWeatherApi.apicall;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public interface GeoApiCallService
{

    /**
     *This method fetch latitude and longitude of cityName
     *
     * @param cityName
     * @return the Map of latitute and longitude
     * @throws JsonProcessingException
     */
    Map<String,String> getCoordinates(String cityName, RestTemplate restTemplate, ObjectMapper mapper) throws JsonProcessingException;
}
