package com.software_engineering.tap.ExplorePage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.software_engineering.tap.R;

import java.util.List;

public class exp_adapter extends RecyclerView.Adapter<exp_adapter.mViewHolder>{
    private Context context;
    private List<Coin> CoinList;

    public class mViewHolder extends RecyclerView.ViewHolder{
        public TextView head, rate;

        public mViewHolder(View view){
            super(view);
            head = (TextView) view.findViewById(R.id.head);
            rate = (TextView) view.findViewById(R.id.rate);
        }

    }

    public exp_adapter(List<Coin> Coinlist){
        this.CoinList = Coinlist;

    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exp_recycle, parent,false);
        return new mViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position){
        Coin coin = CoinList.get(position);
        holder.head.setText(coin.getName());
        String rateStr = new Double(coin.getRate()).toString();
        holder.rate.setText(rateStr);

    }

    @Override
    public int getItemCount(){
        return CoinList.size();
    }






}
