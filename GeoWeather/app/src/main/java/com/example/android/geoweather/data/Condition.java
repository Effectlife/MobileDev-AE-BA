package com.example.android.geoweather.data;

import org.json.JSONObject;

public class Condition implements JSONPopulator{
    private int code;
    private int temperature;
    private String description;

    @Override
    public void populate(JSONObject data) {
        code = data.optInt("code");
        temperature = data.optInt("temp");
        description= data.optString("text");
    }

    public int getCode() {
        return code;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }
}
