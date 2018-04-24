package com.software_engineering.tap.AccountPage;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.software_engineering.tap.R;

import java.util.List;

//***Created by Michael on 20180418***
//***Modified by Michael on20180419***


public class Acc_Adapter extends RecyclerView.Adapter<Acc_Adapter.MyViewHolder>{
    private Context context;
    private List<User> AccountList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView balance, user, table, profile ;

        public MyViewHolder(View view){
            super(view);
            balance = (TextView) view.findViewById(R.id.balance);
            user = (TextView) view.findViewById(R.id.user);
            table = (TextView) view.findViewById(R.id.table);
            profile = (TextView) view.findViewById(R.id.profile);

        }
    }
    public Acc_Adapter(List<User> AccountList){
        this.AccountList = AccountList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View ItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_account, parent, false);

        return new MyViewHolder(ItemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        User user = AccountList.get(position);
        holder.user.setText(user.userName);
        holder.balance.setText(String.valueOf(user.balance));
        //holder.table.setText(user.getTable());
        //holder.profile.setText(user.getProfile());
    }

    @Override
    public int getItemCount(){
        return AccountList.size();
    }
}
