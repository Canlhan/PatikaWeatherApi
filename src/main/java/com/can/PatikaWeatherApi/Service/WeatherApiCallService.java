package com.can.PatikaWeatherApi.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.JSONObject;

import java.util.Map;


public interface WeatherApiCallService
{

    JSONObject getWeatherDailyByCityName(String cityName) throws JsonProcessingException;



    JSONObject getWeatherMonthlyByCityName(String cityName) throws JsonProcessingException;
    JSONObject getWeatherWeeklyByCityName(String cityName) throws JsonProcessingException;


}
