package com.example.android.geoweather.service;

import com.example.android.geoweather.data.Channel;


public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception);
}
