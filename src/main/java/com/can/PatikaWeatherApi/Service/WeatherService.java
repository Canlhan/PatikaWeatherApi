package com.can.PatikaWeatherApi.Service;

import com.can.PatikaWeatherApi.Entity.WeatherInformation;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.parser.ParseException;

public interface WeatherService
{


    WeatherInformation getDailyWeatherByCityName(String cityName) throws JsonProcessingException;

    WeatherInformation getMonthlWeatherByCityName(String cityName) throws  JsonProcessingException;
    WeatherInformation getWeeklyWeatherByCityName(String citName) throws JsonProcessingException;
}
