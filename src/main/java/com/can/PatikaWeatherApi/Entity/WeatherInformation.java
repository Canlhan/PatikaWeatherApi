package com.can.PatikaWeatherApi.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeatherInformation
{


    private String country;
    private String city;

    private Weather daily;

    private List<Weather> monthly;

    private List<Weather> yearly;

}
