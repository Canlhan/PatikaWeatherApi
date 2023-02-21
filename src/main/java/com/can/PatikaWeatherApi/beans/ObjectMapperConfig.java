package com.can.PatikaWeatherApi.beans;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig
{

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }
}
