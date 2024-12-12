package com.example.myapplication;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Parser {

    public List<String> parseJSON(String json) {
        List<String> currencyRates = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject rates = jsonObject.getJSONObject("rates");
            Iterator<String> keys = rates.keys();

            while (keys.hasNext()) {
                String currency = keys.next();
                double rate = rates.getDouble(currency);
                currencyRates.add(currency + " - " + rate);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return currencyRates;
    }
}
