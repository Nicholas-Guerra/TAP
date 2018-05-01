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

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.software_engineering.tap.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
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

        GraphView graph = (GraphView) rootView.findViewById(R.id.graph);
        GridLabelRenderer gridlabel = graph.getGridLabelRenderer();
        gridlabel.setHorizontalAxisTitle("Past 30 Days");
        gridlabel.setVerticalAxisTitle("Price in TAPcoin");
        gridlabel.setHorizontalLabelsVisible(false);
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(nf, nf));

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0,btchistory[0]), new DataPoint(1,btchistory[1]),
                new DataPoint(2,btchistory[2]), new DataPoint(3,btchistory[3]),
                new DataPoint(4,btchistory[4]), new DataPoint(5,btchistory[5]),
                new DataPoint(6,btchistory[6]), new DataPoint(7,btchistory[7]),
                new DataPoint(8,btchistory[8]), new DataPoint(9,btchistory[9]),
                new DataPoint(10,btchistory[10]), new DataPoint(11,btchistory[11]),
                new DataPoint(12,btchistory[12]), new DataPoint(13,btchistory[13]),
                new DataPoint(14,btchistory[14]), new DataPoint(15,btchistory[15]),
                new DataPoint(16,btchistory[16]), new DataPoint(17,btchistory[17]),
                new DataPoint(18,btchistory[18]), new DataPoint(19,btchistory[19]),
                new DataPoint(20,btchistory[20]), new DataPoint(21,btchistory[21]),
                new DataPoint(22,btchistory[22]), new DataPoint(23,btchistory[23]),
                new DataPoint(24,btchistory[24]), new DataPoint(25,btchistory[25]),
                new DataPoint(26,btchistory[26]), new DataPoint(27,btchistory[27]),
                new DataPoint(28,btchistory[28]), new DataPoint(29,btchistory[29])
        });
        graph.setTitle("BTC History");
        graph.addSeries(series);



        // Specify this Adapter
        mAdapter = new exp_adapter(coinList, graph);
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

