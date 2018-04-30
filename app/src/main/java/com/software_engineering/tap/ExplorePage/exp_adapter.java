package com.software_engineering.tap.ExplorePage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.software_engineering.tap.R;

import java.util.List;

public class exp_adapter extends RecyclerView.Adapter<exp_adapter.mViewHolder>{
    private Context context;
    private List<Coin> CoinList;

    public class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView head, rate;

        public mViewHolder(View view){
            super(view);
            view.setOnClickListener(this);
            head = (TextView) view.findViewById(R.id.head);
            rate = (TextView) view.findViewById(R.id.rate);
        }

        @Override
        public void onClick(View view)
        {
            Toast.makeText(view.getContext(),"position =" + getLayoutPosition(), Toast.LENGTH_SHORT).show();
            if(getLayoutPosition() == 0)
            {
                System.out.println("BTC");
            }
            else if(getLayoutPosition() == 1)
            {
                System.out.println("ETH");
            }
            else if(getLayoutPosition() == 2)
            {
                System.out.println("LTC");
            }
            else if(getLayoutPosition() == 3)
            {
                System.out.println("BCH");
            }
            else if(getLayoutPosition() == 4)
            {
                System.out.println("EUR");
            }
            else if(getLayoutPosition() == 5)
            {
                System.out.println("GBP");
            }
            else if(getLayoutPosition() == 6)
            {
                System.out.println("JPY");
            }
            else if(getLayoutPosition() == 7)
            {
                System.out.println("CNY");
            }
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
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getItemCount(){
        return CoinList.size();
    }






}
