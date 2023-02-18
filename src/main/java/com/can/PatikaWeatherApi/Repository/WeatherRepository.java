package com.can.PatikaWeatherApi.Repository;

import com.can.PatikaWeatherApi.Entity.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface WeatherRepository
{

    Weather getDailyWeatherByName(String cityName) throws JsonProcessingException;

    Weather getMonthlyWeatherByName(String cityName);


}
