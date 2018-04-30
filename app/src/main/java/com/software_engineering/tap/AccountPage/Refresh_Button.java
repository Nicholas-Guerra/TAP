package com.software_engineering.tap.AccountPage;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.software_engineering.tap.R;

import java.util.List;

/*Created by Mike 20180429

 */


public class Refresh_Button extends RecyclerView.Adapter<Refresh_Button.MyViewHolder> {


        private Context context;
        private List<User> AccountList;
        private List<Transaction> TransactionList;
        private ViewMore_Adapter.ItemClickListener mClickListener;

        public static class MyViewHolder extends RecyclerView.ViewHolder{
            public TextView RelativeLayout, Balance;

            public MyViewHolder(View view){
                super(view);
                Balance = (TextView) view.findViewById(R.id.balance);
                RelativeLayout = (TextView) view.findViewById(R.id.recent_transactions);

            }
        }

        public Refresh_Button(List<User> AccountList, List<Transaction> TransactionList){
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
            Transaction transaction = TransactionList.get(position);
            //holder.Balance.setText(user.balance);
            holder.RelativeLayout.setText(transaction.transactionID);
        }

        @Override
        public int getItemCount(){return TransactionList.size();}

      /*  public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            TextView mTextView;

            ViewHolder(View itemView){
                super(itemView);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view){
                if (mClickListener != null)
                    mClickListener.onItemClick((view, getAdapterPosition());

            void setClickListener(ItemClickListenerlickListener itemClickeListener) {
                this.mClickListener = itemClickListener;
            }

            public interface ItemClickListener {
                void onItemClick(View view, int position);

            }

        }

    }*/

}
