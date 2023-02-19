package com.can.PatikaWeatherApi.Service;

import com.can.PatikaWeatherApi.Entity.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Map;

@Service
public class WeatherApiCallServiceImpl implements WeatherApiCallService{


    private final String BASE_URL="https://api.openweathermap.org/data/2.5";
    private final String API_KEY="d2171110bcc261d78e7d3a04528fe62e";


    private final ObjectMapper mapper = new ObjectMapper();
    private final RestTemplate restTemplate=new RestTemplate();
     private JSONObject jsonObject;

     private Map<String,String> latAndLon;


    public WeatherApiCallServiceImpl() throws JsonProcessingException {

    }

    @Override
    public JSONObject getWeatherDailyByCityName(String cityName) throws JsonProcessingException {

        latAndLon=getLatituteAndLongitude(cityName);
        String lat=latAndLon.get("lat");
        String lon=latAndLon.get("lon");
        ResponseEntity<String> response=
                restTemplate.getForEntity(BASE_URL+"/weather?lat="+lat+"&lon="+lon+"&units=metric&appid="+API_KEY, String.class);
        JsonNode  root = mapper.readTree(response.getBody());
//        JsonNode country=root.path("location").path("country");

        jsonObject= (JSONObject) JSONValue.parse(root.asText());
       ;
//        Weather weather=getBaseWeatherInformation(root);
//
//        WeatherInformation  weatherInformation=WeatherInformation.builder().daily(weather)
//                .country(country.toString()).city(cityName).build();

        return jsonObject;
    }


    /**
     * This method request to openweatherapi.org  for getting the weather of city
     *
     * @param cityName
     * @return JsonObject
     *
     * @throws JsonProcessingException
     */
    @Override
    public JSONObject getWeatherMonthlyByCityName(String cityName) throws JsonProcessingException {
        latAndLon=getLatituteAndLongitude(cityName);
        String lat=latAndLon.get("lat");
        String lon=latAndLon.get("lon");
        ResponseEntity<String> response=
                restTemplate.getForEntity("https://pro.openweathermap.org/data/2.5/forecast/climate?lat="+lat+"&lon="+lon+"&appid=b1b15e88fa797225412429c1c50c122a1", String.class);
        JsonNode  root = mapper.readTree(response.getBody());

         jsonObject= (JSONObject) JSONValue.parse(root.asText());



        return jsonObject;
    }

    @Override
    public JSONObject getWeatherWeeklyByCityName(String cityName) throws JsonProcessingException {
        latAndLon=getLatituteAndLongitude(cityName);
        String lat=latAndLon.get("lat");
        String lon=latAndLon.get("lon");

        String url=BASE_URL+"/forecast?lat="+lat+"&lon="+lon+"&cnt=7&units=metric&appid="+API_KEY;
        ResponseEntity<String> response=restTemplate.getForEntity(url, String.class);
        JsonNode  root = mapper.readTree(response.getBody());

        jsonObject= (JSONObject) JSONValue.parse(root.toString());
        System.out.println("roottt: "+root);



        return jsonObject;
    }

    /**
     *This method fetch latitude and longitude of cityName
     *
     * @param cityName
     * @return the Map of latitute and longitude
     * @throws JsonProcessingException
     */

    public Map<String, String> getLatituteAndLongitude(String cityName) throws JsonProcessingException {
        String latAndLon="https://api.openweathermap.org/geo/1.0/direct?q="+cityName+"&limit=1&appid=d2171110bcc261d78e7d3a04528fe62e";
        ResponseEntity<String> response=restTemplate.getForEntity(latAndLon,String.class);
        JsonNode  root = mapper.readTree(response.getBody());

        String lat=root.path("lat").toString();
        String lon=root.path("lon").toString();

        Map<String,String> latAndLonMap=Map.of("lat",lat,
                                              "lon",lon );

        return latAndLonMap;
    }

    private Weather getBaseWeatherInformation(JsonNode root) throws JsonProcessingException {


        JsonNode name = root.path("current");
        String date=root.path("location").path("localtime").toString().substring(1).split(" ")[0];
    LocalDate localDate=LocalDate.parse(date);
        JsonNode temperature= name.path("temp_c");
        JsonNode description=name.path("condition").path("text");
        JsonNode windSpeed=name.path("wind_kph");
        JsonNode pressure=name.path("pressure_mb");
        Weather weather=Weather.builder().temperature(temperature.toString())
                .description(Map.of("description",description.toString(),
                        "windSpeed",windSpeed.toString(),
                        "pressure",pressure.toString() )).localDate(localDate).build();
        System.out.println(root);

        return weather;
    }
}
