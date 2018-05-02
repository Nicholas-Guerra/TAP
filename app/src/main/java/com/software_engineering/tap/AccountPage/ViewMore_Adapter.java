package com.software_engineering.tap.AccountPage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.software_engineering.tap.Main_Notifications_Settings.Transaction_Notification;
import com.software_engineering.tap.R;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by grant_000 on 4/11/2018.
 */

public class ViewMore_Adapter extends RecyclerView.Adapter<ViewMore_Adapter.ViewHolder> {

    private List<Transaction> transactionList;
    private LayoutInflater mInflaters;
    private ItemClickListener mClickListener;
    private Context context;
    private SimpleDateFormat format;


    // data is passed into the constructor
    public ViewMore_Adapter(Context context, List<Transaction> transaction_List) {
        this.mInflaters = LayoutInflater.from(context);
        this.context = context;
        transactionList = transaction_List;
        format = new SimpleDateFormat("MM/dd/yy\nh:mm a");

    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewmore_adapter, parent, false));
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder obholder, int position) {
        if(transactionList == null) {
            obholder.fromName.setText("No Transactions To Display");
            obholder.dateView.setText("No Transactions To Display");
            obholder.amountView.setText("No Transactions To Display");
            obholder.statusView.setText("No Transactions To Display");
        }
        else{
            obholder.fromName.setText(transactionList.get(position).toFromName);
            obholder.fromName.setSelected(true);
            obholder.dateView.setText(format.format(new Date(transactionList.get(position).date)));
            obholder.amountView.setText(String.valueOf(transactionList.get(position).amount));
            obholder.statusView.setText(transactionList.get(position).status);


            if (transactionList.get(position).amount > 0) {
                obholder.amountView.setTextColor(context.getResources().getColor(R.color.colorDeposit));
            } else {
                obholder.amountView.setTextColor(context.getResources().getColor(R.color.colorDeduct));
            }
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {

        return transactionList.size();
    }
    public void addItems(List<Transaction> transaction_List) {
        this.transactionList = transaction_List;
        notifyDataSetChanged();
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView fromName;
        private TextView amountView;
        private TextView statusView;
        private TextView dateView;
        private RelativeLayout relativeLayoutView;

        ViewHolder(View itemView) {
            super(itemView);
            fromName = itemView.findViewById(R.id.fromName);
            itemView.setOnClickListener(this);

            amountView = itemView.findViewById(R.id.amountView);
            itemView.setOnClickListener(this);

            statusView = itemView.findViewById(R.id.statusView);
            itemView.setOnClickListener(this);

            dateView = itemView.findViewById(R.id.dateView);
            itemView.setOnClickListener(this);

            relativeLayoutView = itemView.findViewById(R.id.vmRelativeLayout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }


}
