package com.software_engineering.tap.ExplorePage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
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
        double btchistory[] = new double[]{ 6926.02,
                6816.74,7049.79,7417.89,6789.30,6774.75,6620.41,6896.28,7022.71,
                6773.94,6830.90,6939.55,7916.37,7889.23,8003.68,8357.04,7890.15,
                8163.69,8373.74,8863.50,8917.60,8792.63,8938.30,9652.16,8864.09,
                9279.00,8978.33,9342.47,9392.03,9314.39};
        double ethhistory[] = new double[]{403.11,
                364.77,391.73,406.30,376.37,380.39,367.15,386.97,401.62,396.42,
                405.83,420.71,463.98,514.14,492.46,522.74,503.76,513.49,511.00,
                553.86,588.93,602.99,633.63,643.71,699.05,618.01,631.29,674.25,
                688.18,680.94};
        double ltchistory[] = new double[]{116.02,
                120.96,111.34,120.18,126.74,117.69,119.34,113.79,119.38,117.40,
                114.97,114.17,114.96,120.62,130.82,125.43,130.58,126.23,136.72,
                136.09,142.21,149.97,148.94,149.71,151.17,162.37,145.96,146.96,
                150.49,152.24};
        double bchhistory[] = new double[]{708.98,
                656.34,669.68,711.16,652.89,630.16,607.08,650.54,653.51,639.36,
                648.35,655.83,703.67,763.08,730.88,772.52,751.58,777.17,847.74,
                975.67,1099.27,1149.86,1233.71,1356.53,1484.87,1295.2,1329.80,
                1386.36,1403.51,1435.03,1390.13};
        double eurhistory[] = new double[]{};
        double gbphistory[] = new double[]{};
        double jpyhistory[] = new double[]{};
        double cnyhistory[] = new double[]{};


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
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),1);
        mRecyclerView.addItemDecoration(mDividerItemDecoration);
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

