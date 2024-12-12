package com.example.myapplication;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.List;

public class DataLoader {

    private Context context;

    public DataLoader(Context context) {
        this.context = context;
    }

    public interface Callback {
        void onDataLoaded(List<String> rates);
        void onError(String error);
    }

    public void fetchCurrencyRates(String url, final Callback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Parser parser = new Parser();
                        List<String> rates = parser.parseJSON(response);
                        callback.onDataLoaded(rates);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError(error.toString());
            }
        });

        queue.add(stringRequest);
    }
}
