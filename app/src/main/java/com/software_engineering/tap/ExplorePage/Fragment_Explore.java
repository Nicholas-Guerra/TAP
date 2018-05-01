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
        /*double ethhistory[] = new double[]{403.11,
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
        double eurhistory[] = new double[]{1.2148,1.2107,1.2165,1.2234,1.2210,1.2313,1.348,1.2378,
                1.2372,1.2382, 1.2348,1.2327,1.2368,1.2354,1.2322,1.2299,1.2240,1.2282,1.2271,2.2304,
                1.2331,1.2302,1.2311,1.2408,1.2449,1.2357,1.2322,1.2343,1.2247,1.2336,1.2288};
        double gbphistory[] = new double[]{1.3776,1.3780,1.3916,1.3932,1.3979,1.3939,1.4006,1.4004,
                1.4086,1.4203,1.4288,1.4339,1.4244,1.4242,1.4232,1.4178,1.4177,1.4129,1.4089,1.4087,
                1.4005,1.4082,1.4060,1.4050,1.4031,1.4016,1.4031,1.4080,1.4162,1.4227};
        double jpyhistory[] = new double[]{.0092,.0092,.0092,.0092,.0091,.0092,.0093,.0093,.0093,
                .0093,.0093,.0093,.0093,.0093,.0093,.0093,.0093,.0094,.0093,.0094,.0094,.0094,.0094,.0093,
                .0094,.0094,.0095,.0094,.0094,.0094};
        double cnyhistory[] = new double[]{6.3330,6.3330,6.3599,6.3259,6.3063,6.3171,6.2965,6.2965,
                6.2791,6.2744,6.2820,6.2946,6.2744,6.2820,6.2946,6.2749,6.2749,6.2747,6.2909,6.2686,
                6.819,6.3100,6.3020,6.3020,6.3020,6.3060,6.3060,6.2866,6.2866,6.2866,6.2892,6.2975};
        */

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

