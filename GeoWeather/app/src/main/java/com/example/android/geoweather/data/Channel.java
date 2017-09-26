package com.example.android.geoweather.data;

import org.json.JSONObject;

public class Channel implements JSONPopulator{
    private Item item;
    private Units units;

    public Item getItem() {
        return item;
    }

    public Units getUnits() {
        return units;
    }

    @Override
    public void populate(JSONObject date) {
        units = new Units();
        units.populate(date.optJSONObject("units"));

        item = new Item();
        item.populate(date.optJSONObject("item"));


    }
}
