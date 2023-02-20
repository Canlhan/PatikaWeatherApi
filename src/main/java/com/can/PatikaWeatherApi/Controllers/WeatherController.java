package com.can.PatikaWeatherApi.Controllers;

import com.can.PatikaWeatherApi.Entity.WeatherInformation;
import com.can.PatikaWeatherApi.Service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class WeatherController
{


    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherApiCallService) {
        this.weatherService = weatherApiCallService;
    }

    //http://localhost:8086/api/v1/daily?cityName=izmir
    @GetMapping("/daily")
    public  ResponseEntity<WeatherInformation> getWeatherDaily(@RequestParam String cityName) {

        try {
            return ResponseEntity.ok(weatherService.getDailyWeatherByCityName(cityName));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    //http://localhost:8086/api/v1/monthly?cityName=izmir
    @GetMapping("/monthly")
    public ResponseEntity<WeatherInformation> getWeatherMonthly(@RequestParam String cityName){


        try {
            return ResponseEntity.ok(weatherService.getMonthlWeatherByCityName(cityName));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    //http://localhost:8086/api/v1/weekly?cityName=izmir
    @GetMapping("/weekly")

    public ResponseEntity<WeatherInformation> getWeatherWeekly(@RequestParam  String cityName){


        try {
            return ResponseEntity.ok(weatherService.getWeeklyWeatherByCityName(cityName));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }






}
