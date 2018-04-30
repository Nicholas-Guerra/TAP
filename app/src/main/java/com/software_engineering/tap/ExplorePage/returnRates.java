package com.software_engineering.tap.ExplorePage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class returnRates 
{
    public double rates(String code)
    {
        String result;
        String url = "https://min-api.cryptocompare.com/data/price?fsym=USD&tsyms=BTC,ETH,LTC,BCH,EUR,GBP,JPY,CNY";
        double rateValue = 0;
        
        fetchRates rateGet = new fetchRates();


        try {
            result = rateGet.execute(url).get();
            JSONObject jo = new JSONObject(result);
            rateValue = (double) jo.get(code);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rateValue;
    }
}
