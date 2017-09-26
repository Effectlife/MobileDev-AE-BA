package com.example.android.geoweather.data;

import org.json.JSONObject;

public class Item implements JSONPopulator{
    private Condition condition;
    @Override
    public void populate(JSONObject date) {
        condition = new Condition();
        condition.populate(date.optJSONObject("condition"));
    }

    public Condition getCondition() {
        return condition;
    }
}
