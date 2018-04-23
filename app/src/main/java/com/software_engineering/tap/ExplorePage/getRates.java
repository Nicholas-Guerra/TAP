/*package com.software_engineering.tap.ExplorePage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.*;
import java.net.*;
import java.util.*;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class getRates
{
    private static final MediaType JSON =  MediaType.parse("application/json; charset= utf-8");
    public double pullRates(String Param1) throws IOException, JSONException {


        String coinAPI ="https://www.rest.coinapi.io/v1/exchangerate";
        String charset ="UTF-8";
        String Param2 = "USD";
        JSONObject json = new JSONObject();


        String Query = String.format("/%s/%s/", URLEncoder.encode(Param1,charset),
                URLEncoder.encode(Param2,charset));
        RequestBody body = RequestBody.create(JSON, String.valueOf(json));

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(coinAPI+Query)
                .post(body)
                .addHeader("X-CoinAPI-Key","F61688EA-F08B-484D-B0A9-A517398A603F")
                .build();
        Response response = client.newCall(request).execute();

        String responseStr = response.body().string();


        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(responseStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Double rate =  jsonObject.getDouble("rate");

        return rate;




    }


}
*/