package com.can.PatikaWeatherApi.Entity;

import lombok.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
@ToString
@Builder
public class Weather
{

    private Map<String,String> description;
    private String temperature;
    private LocalDate localDate;

    public Weather(Map<String,String> description,String temperature,LocalDate localDate) {
        this.description =description;
        this.temperature=temperature;
        this.localDate=localDate;
    }

}
