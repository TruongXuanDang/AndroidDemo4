package com.truongdx8.androidproject4;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiManager {
    String WEATHERSERVER = "http://dataservice.accuweather.com";

    @GET("/forecasts/v1/hourly/12hour/353412?apikey=GxeywLDUyE32YK5THyh9wf8jGqQ2EYpN&language=vi-vn&metric=true")
    Call<List<WeatherItem>> getWeatherItems();
}
