package com.can.PatikaWeatherApi.Repository;

import com.can.PatikaWeatherApi.Entity.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class WeatherRepositoryImpl implements WeatherRepository
{

    private final RestTemplate restTemplate=new RestTemplate();
    private final String BASE_URL="http://api.weatherstack.com/";
    static String url="http://api.weatherstack.com/current?access_key=1e2fe1bdd673d276f3d28247420e2d87&query=manisa";
    @Override
    public Weather getDailyWeatherByName(String cityName) throws JsonProcessingException {
        ResponseEntity<String> response
                = restTemplate.getForEntity(url, String.class);


        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree(response.getBody());
        JsonNode name = root.path("current");
        JsonNode temperature=name.path("temperature");


        return Weather.builder().city(temperature.toString()).build();
    }

    @Override
    public Weather getMonthlyWeatherByName(String cityName) {
        return null;
    }
}
