package com.can.PatikaWeatherApi.Controllers;

import com.can.PatikaWeatherApi.Repository.WeatherRepository;
import com.can.PatikaWeatherApi.Repository.WeatherRepositoryImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Main
{
     static RestTemplate restTemplate=new RestTemplate();
    static String url="http://api.weatherstack.com/current?access_key=1e2fe1bdd673d276f3d28247420e2d87&query=manisa";
    public static void main(String[] args) throws JsonProcessingException {

        WeatherRepository weatherRepository=new WeatherRepositoryImpl();

        System.out.println(weatherRepository.getDailyWeatherByName("izmir"));
    }
}
