package com.can.PatikaWeatherApi.Controllers;

import com.can.PatikaWeatherApi.Entity.WeatherInformation;
import com.can.PatikaWeatherApi.Service.WeatherApiCallService;
import com.can.PatikaWeatherApi.Service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1")
public class WeatherController
{


    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherApiCallService) {
        this.weatherService = weatherApiCallService;
    }

    //http://localhost:8086/api/v1/daily
    @GetMapping("/daily")
    public  ResponseEntity<WeatherInformation> getWeatherDaily(@RequestParam String cityName) {

        try {
            return ResponseEntity.ok(weatherService.getDailyWeatherByCityName(cityName));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/monthly")
    public ResponseEntity<WeatherInformation> getWeatherMonthly(@RequestParam String cityName){


        try {
            return ResponseEntity.ok(weatherService.getMonthlWeatherByCityName(cityName));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }






}
