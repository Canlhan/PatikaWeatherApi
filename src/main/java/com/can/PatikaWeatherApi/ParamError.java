package com.can.PatikaWeatherApi;

public class ParamError extends RuntimeException{


    private String message;

    public ParamError(String message) {
        super(message);

    }
}
