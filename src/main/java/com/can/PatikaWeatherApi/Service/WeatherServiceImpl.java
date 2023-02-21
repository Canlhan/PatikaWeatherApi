package com.can.PatikaWeatherApi.Service;


import com.can.PatikaWeatherApi.Entity.Weather;
import com.can.PatikaWeatherApi.Entity.WeatherInformation;
import com.can.PatikaWeatherApi.ParamError;
import com.can.PatikaWeatherApi.apicall.WeatherApiCallService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@Service
public class WeatherServiceImpl implements WeatherService{


    private final WeatherApiCallService weatherApiCallService;


    public WeatherServiceImpl(WeatherApiCallService weatherApiCallService) {
        this.weatherApiCallService = weatherApiCallService;
    }


    @Override
    public WeatherInformation getDailyWeatherByCityName(String cityName) throws JsonProcessingException, ParamError {

        if(cityName.equals("")){
            throw  new ParamError("city name mustnt be empty");
        }
        JSONObject jsonObject=weatherApiCallService.getWeatherDailyByCityName(cityName);

        JSONObject  sys=(JSONObject)jsonObject.get("sys");
        String country=sys.get("country").toString();
        JSONObject main=(JSONObject)jsonObject.get("main");
        String temperature=main.get("temp").toString();
        List<JSONObject> weatherArray= (List<JSONObject>) jsonObject.get("weather");
        String description=weatherArray.get(0).get("description").toString();
        Long dateFromApi=(long)jsonObject.get("dt");
        Date date=new Date(dateFromApi*1000);

        Weather weather=Weather.builder().localDate(date).temperature(temperature).description(description).build();
        WeatherInformation weatherInformation=WeatherInformation.builder()
                .city(cityName).country(country).daily(weather).build();

        return weatherInformation;




    }

    @Override
    public WeatherInformation getMonthlWeatherByCityName(String cityName) throws JsonProcessingException {

        if(cityName.equals("")){
            throw  new ParamError("city name mustnt be empty");
        }
        JSONObject jsonObject=weatherApiCallService.getWeatherMonthlyByCityName(cityName);
        JSONObject city=(JSONObject) jsonObject.get("city");
        String country=city.get("country").toString();
        List<JSONObject> dayList=(List<JSONObject>)jsonObject.get("list");

        List<Weather> dayWeatherList=converMonthlyListOfJsonObjectToListOfWeather(dayList);

        WeatherInformation weatherInformation=WeatherInformation.builder()
                .monthly(dayWeatherList).country(country).city(cityName).build();
        System.out.println("monthly: "+ jsonObject);






        return weatherInformation;
    }

    @Override
    public WeatherInformation getWeeklyWeatherByCityName(String citName) throws JsonProcessingException {

        if(citName.equals("")){
            throw  new ParamError("city name mustnt be empty");
        }



        JSONObject jsonObject=weatherApiCallService.getWeatherWeeklyByCityName(citName);
        JSONObject city=(JSONObject) jsonObject.get("city");
        String country=city.get("country").toString();
        System.out.println("json week: "+jsonObject);
        List<JSONObject> days=(List<JSONObject>) jsonObject.get("list");

        List<Weather> weeklyWeather=convertWeeklyListOfJsonObjectToListOfWeather(days);

        WeatherInformation weatherInformation=WeatherInformation.builder()
                .city(citName).country(country).weekly(weeklyWeather).build();

        return weatherInformation;
       // JSONObject main=(JSONObject)jsonObject.get("main");
//        String temperature=main.get("temp").toString();
//        List<JSONObject> weatherArray= (List<JSONObject>) jsonObject.get("weather");
//        String description=weatherArray.get(0).get("description").toString();
//        Long dateFromApi=(long)jsonObject.get("dt");
//        Date date=new Date(dateFromApi*1000);

    }

    /**
     * this method convert coming list of JsonObject to list of Weather
     *
     * @param days
     * @return List of Weather
     */
    private List<Weather> convertWeeklyListOfJsonObjectToListOfWeather(List<JSONObject> days){

        List<Weather> weathersOfWeek=new ArrayList<>();

        for (int i=0;i<days.size();i+=8){

                JSONObject  baseDay=(JSONObject) days.get(i);
                JSONObject day=(JSONObject)baseDay.get("main");
                String temperature=day.get("temp").toString();
                List<JSONObject> weatherList=(List<JSONObject>) baseDay.get("weather");
                String description=weatherList.get(0).get("description").toString();
                Long dateFromApi=(long)baseDay.get("dt");
                Date date=new Date(dateFromApi*1000);

                Weather weather=Weather.builder().description(description)
                        .localDate(date).temperature(temperature).build();
                weathersOfWeek.add(weather);

        }
        return weathersOfWeek;
    }

    private List<Weather> converMonthlyListOfJsonObjectToListOfWeather(List<JSONObject> days){
        List<Weather> weathersOfMonthyl=new ArrayList<>();

        for (int i=0;i<days.size();i++){

            JSONObject  baseDay=(JSONObject) days.get(i);
            JSONObject day=(JSONObject)baseDay.get("temp");
            String temperature=day.get("day").toString();
            List<JSONObject> weatherList=(List<JSONObject>) baseDay.get("weather");
            String description=weatherList.get(0).get("description").toString();
            Long dateFromApi=(long)baseDay.get("dt");
            Date date=new Date(dateFromApi*1000);

            Weather weather=Weather.builder().description(description)
                    .localDate(date).temperature(temperature).build();
            weathersOfMonthyl.add(weather);

        }
        return weathersOfMonthyl;

    }

}
