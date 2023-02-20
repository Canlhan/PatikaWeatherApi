package com.can.PatikaWeatherApi.Entity;

import lombok.*;

import java.time.LocalDate;
import java.util.*;

@Data
@ToString
@Builder
public class Weather
{

    private String description;
    private String temperature;
    private Date localDate;

    public Weather(String description,String temperature,Date localDate) {
        this.description =description;
        this.temperature=temperature;
        this.localDate=localDate;
    }

}
