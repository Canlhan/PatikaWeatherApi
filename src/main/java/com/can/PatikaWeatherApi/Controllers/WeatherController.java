package com.can.PatikaWeatherApi.Controllers;


import com.can.PatikaWeatherApi.Entity.WeatherInformation;
import com.can.PatikaWeatherApi.ExceptionModel.ExceptionResponse;
import com.can.PatikaWeatherApi.ParamError;
import com.can.PatikaWeatherApi.Service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.Nonnull;
import lombok.NonNull;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class WeatherController
{


    private final WeatherService weatherService;

    private WeatherInformation weatherInformation;
    public WeatherController(WeatherService weatherApiCallService) {
        this.weatherService = weatherApiCallService;
        this.weatherInformation=new WeatherInformation();
    }

    //http://localhost:8086/api/v1/daily?cityName=izmir
    @GetMapping("/daily")

    public  ResponseEntity<?> getWeatherDaily(@RequestParam @Nonnull  String cityName) throws JsonProcessingException {

        try{
            weatherInformation=  weatherService.getDailyWeatherByCityName(cityName);
        }catch (Exception e){

            return  ResponseEntity.badRequest().body(ExceptionResponse.builder().message("Param is not valid ").build());
        }

            return ResponseEntity.ok(weatherInformation);





    }

    //http://localhost:8086/api/v1/monthly?cityName=izmir
    @GetMapping("/monthly")

    public ResponseEntity<?> getWeatherMonthly(@RequestParam @Nonnull String cityName){


        try {
           weatherInformation=weatherService.getMonthlWeatherByCityName(cityName);
        } catch (ParamError | JsonProcessingException e) {
            return  ResponseEntity.badRequest().body(ExceptionResponse.builder().message("Param is not valid ").build());
        }
        return ResponseEntity.ok(weatherInformation);
    }
    //http://localhost:8086/api/v1/weekly?cityName=izmir
    @GetMapping("/weekly")

    public ResponseEntity<?> getWeatherWeekly(@RequestParam @Nonnull String cityName){


        try {
            weatherService.getWeeklyWeatherByCityName(cityName);
        } catch (JsonProcessingException | ParamError e) {
            return  ResponseEntity.badRequest().body(ExceptionResponse.builder().message("Param is not valid ").build());
        }
        return ResponseEntity.ok(weatherInformation);
    }






}
