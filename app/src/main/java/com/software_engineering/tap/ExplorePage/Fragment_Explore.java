package com.software_engineering.tap.ExplorePage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.software_engineering.tap.R;

import java.util.ArrayList;
import java.util.List;

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


        // Specify this Adapter
        mAdapter = new exp_adapter(coinList);
        mRecyclerView.setAdapter(mAdapter);

        //Set Layout Manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Set Animations
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        createCoinData();

        return rootView;

    }

    private void createCoinData()
    {
        Coin coin = new Coin("BTC",.00012);
        coinList.add(coin);

        coin = new Coin("ETH",.001946 );
        coinList.add(coin);

        coin = new Coin("LTC",.007298);
        coinList.add(coin);
    }

}

