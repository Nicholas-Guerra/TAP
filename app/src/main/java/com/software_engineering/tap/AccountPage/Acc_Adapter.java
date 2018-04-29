package com.software_engineering.tap.AccountPage;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import android.widget.TextView;

import com.software_engineering.tap.R;

import java.util.List;

//***Created by Michael on 20180418***
//***Modified by Michael on 20180419***
//***Modified by Michael on 20180423***


public class Acc_Adapter extends RecyclerView.Adapter<Acc_Adapter.MyViewHolder>{
    private Context context;
    private List<User> AccountList;
    private List<Transaction> TransactionList;
    private ViewMore_Adapter.ItemClickListener mClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView Balance, Table;

        public MyViewHolder(View view){
            super(view);
            //Balance = (TextView) view.findViewById(R.id.balance);
            Table = (TextView) view.findViewById(R.id.recent_transactions);

        }
    }
   // public Acc_Adapter(List<User> AccountList){
 //      this.AccountList = AccountList;
  //  }
    public Acc_Adapter(List<Transaction> TranscationList){
        this.TransactionList = TransactionList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View ItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_account, parent, false);

        return new MyViewHolder(ItemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
    //    User user = AccountList.get(position);
        String transaction = TransactionList.get(position);
    //    holder.Balance.setText(user.balance);
        holder.Table.setText(transaction.table);
    }

    @Override
    public int getItemCount(){
        return AccountList.size();
    }

    void setClickListener(ViewMore_Adapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
