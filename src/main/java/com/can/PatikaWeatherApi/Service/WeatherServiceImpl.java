package com.can.PatikaWeatherApi.Service;

import com.can.PatikaWeatherApi.Entity.WeatherInformation;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService{


    private final WeatherApiCallService weatherApiCallService;


    public WeatherServiceImpl(WeatherApiCallService weatherApiCallService) {
        this.weatherApiCallService = weatherApiCallService;
    }

    @Override
    public WeatherInformation getDailyWeatherByCityName(String cityName) throws JsonProcessingException {

        System.out.println("daily"+weatherApiCallService.getWeatherDailyByCityName(cityName));

        return null;

    }

    @Override
    public WeatherInformation getMonthlWeatherByCityName(String cityName) throws JsonProcessingException {

        System.out.println("monthly: "+ weatherApiCallService.getWeatherMonthlyByCityName(cityName));
        return null;
    }

    @Override
    public WeatherInformation getWeeklyWeatherByCityName(String citName) throws JsonProcessingException, ParseException {


        JSONObject root= weatherApiCallService.getWeatherWeeklyByCityName(citName);


        JSONObject forecast=(JSONObject) root.get("forecast");
        System.out.println("root weekly: "+forecast);




        return null;
    }
}
