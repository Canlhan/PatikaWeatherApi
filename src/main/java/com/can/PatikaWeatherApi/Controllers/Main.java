package com.can.PatikaWeatherApi.Controllers;

import com.can.PatikaWeatherApi.apicall.WeatherApiCallServiceImpl;
import com.can.PatikaWeatherApi.Service.WeatherService;
import com.can.PatikaWeatherApi.Service.WeatherServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.parser.ParseException;
import org.springframework.web.client.RestTemplate;

public class Main
{
     static RestTemplate restTemplate=new RestTemplate();
    static String url="http://api.weatherstack.com/current?access_key=1e2fe1bdd673d276f3d28247420e2d87&query=manisa";
    public static void main(String[] args) throws JsonProcessingException, ParseException {
        //WeatherService weatherApiCallService=new WeatherServiceImpl(new WeatherApiCallServiceImpl(geoApiCallService));


//
//        System.out.println("reponse: "+weatherApiCallService.getDailyWeatherByCityName("izmir"));
//
//        System.out.println("reponse mont: "+weatherApiCallService.getMonthlWeatherByCityName("manisa"));

      // System.out.println("daily weather: "+weatherApiCallService.getDailyWeatherByCityName("İstanbul"));
       //System.out.println("weekly weather: "+weatherApiCallService.getWeeklyWeatherByCityName("İstanbul"));

      //  System.out.println("monthly: "+weatherApiCallService.getMonthlWeatherByCityName("İzmir"));









//        ResponseEntity<String> response
//                = restTemplate.getForEntity(url + "/1", String.class);
//
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode root = mapper.readTree(response.getBody());
//        JsonNode name = root.path("current");
//
//        System.out.println(name);

    }
}
