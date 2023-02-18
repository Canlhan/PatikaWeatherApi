package com.can.PatikaWeatherApi.Entity;

import lombok.*;

import java.util.Set;

@Data
@ToString
@NoArgsConstructor
@Builder
public class Weather
{
    private Long id;
    private  String country;
    private String city;

    private Set<String> daily;
    private Set<String> monthly;


    public Weather(Long id, String country, String city, Set<String> daily, Set<String> monthly) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.daily = daily;
        this.monthly = monthly;
    }
}
