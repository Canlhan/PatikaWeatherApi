package com.can.PatikaWeatherApi.apicall;

import com.can.PatikaWeatherApi.ParamError;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class GeoApiCallServiceImpl implements GeoApiCallService{


    @Override
    public Map<String, String> getCoordinates(String cityName, RestTemplate restTemplate, ObjectMapper mapper) throws JsonProcessingException {
        if(cityName.equals("")){
            throw  new ParamError("city name mustnt be empty");
        }
        String latAndLon="https://api.openweathermap.org/geo/1.0/direct?q="+cityName+"&limit=1&appid=d2171110bcc261d78e7d3a04528fe62e";
        ResponseEntity<String> response=restTemplate.getForEntity(latAndLon,String.class);
        JsonNode root = mapper.readTree(response.getBody());

        String lat=root.get(0).path("lat").toString();
        String lon=root.get(0).path("lon").asText();
        System.out.println("lat: "+lat);
        Map<String,String> latAndLonMap=Map.of("lat",lat,
                "lon",lon );

        return latAndLonMap;
    }
}
