package com.software_engineering.tap.ExplorePage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.software_engineering.tap.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Fragment_Explore extends Fragment{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Coin> coinList = new ArrayList<>();

    public Fragment_Explore() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_explore, container, false);
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.Exp_recycleview);

        GraphView graph = (GraphView) rootView.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3)
        });
        graph.addSeries(series);


        // Specify this Adapter
        mAdapter = new exp_adapter(coinList);
        mRecyclerView.setAdapter(mAdapter);

        //Set Layout Manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Set Animations
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this , LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        

        createCoinData();
        mAdapter.notifyDataSetChanged();

        return rootView;

    }

    private void createCoinData() {

        String result;
        String url = "https://min-api.cryptocompare.com/data/price?fsym=USD&tsyms=BTC,ETH,LTC,BCH,EUR,GBP,JPY,CNY";

        fetchRates rateGet = new fetchRates();
        try {
            //parse HTTP
            result = rateGet.execute(url).get();
            JSONObject jo = new JSONObject(result);

            coinList.clear();

            Coin coin = new Coin("BTC",jo.get("BTC").toString());
            coinList.add(coin);

            coin = new Coin("ETH",jo.get("ETH").toString());
            coinList.add(coin);

            coin = new Coin("LTC",jo.get("LTC").toString());
            coinList.add(coin);

            coin = new Coin("BCH",jo.get("BCH").toString());
            coinList.add(coin);

            coin = new Coin("EUR",jo.get("EUR").toString());
            coinList.add(coin);

            coin = new Coin("GBP",jo.get("GBP").toString());
            coinList.add(coin);

            coin = new Coin("CNY",jo.get("CNY").toString());
            coinList.add(coin);

            coin = new Coin("JPY",jo.get("JPY").toString());
            coinList.add(coin);



        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

}

