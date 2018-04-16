package com.software_engineering.tap.Main_Notifications_Settings;


import
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.software_engineering.tap.R;
import com.software_engineering.tap.TransactionPage.DialogFragment_Authentication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RecyclerViewAdpater_Notifications  extends RecyclerView.Adapter<RecyclerViewAdpater_Notifications.RecyclerViewHolder>{


    private List<Transaction_Notification> transactionNotificationList;
    private DateFormat df = new SimpleDateFormat("EEE MM/dd/yy hh:mm aaa");
    private Activity activity;
    public RecyclerViewAdpater_Notifications(Activity activity, List<Transaction_Notification> transaction_notificationList) {
        transactionNotificationList = transaction_notificationList;
        this.activity = activity;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewmodel_transaction_notification, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        holder.toName.setText(transactionNotificationList.get(position).toName);
        holder.amount.setText(String.valueOf(transactionNotificationList.get(position).amount));
        holder.date.setText(df.format(new Date(transactionNotificationList.get(position).date)));


        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);

                builder.setTitle("Confirm");
                builder.setMessage("Send " + holder.toName.getText() + " " + holder.amount.getText() + " TPC?");

                builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        final DialogFragment_Authentication dialogFragment_authentication = new DialogFragment_Authentication();
                        dialogFragment_authentication.show(activity.getFragmentManager(), "authentication");
                        activity.getFragmentManager().executePendingTransactions();
                        dialogFragment_authentication.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialogInterface) {
                                if(dialogFragment_authentication.getSuccess()){
                                    Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show();
                                    //Authentication successful


                                } else{
                                    Toast.makeText(activity, "Canceled", Toast.LENGTH_SHORT).show();
                                    //Authentication canceled

                                }
                            }
                        });

                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("Decline", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });




    }

    @Override
    public int getItemCount() {
        return transactionNotificationList.size();
    }

    public void addItems(List<Transaction_Notification> transaction_notificationList) {
        this.transactionNotificationList = transaction_notificationList;
        notifyDataSetChanged();
    }


    static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView toName, amount, date;
        private RelativeLayout relativeLayout;


        RecyclerViewHolder(View view) {
            super(view);

            toName = view.findViewById(R.id.toName);
            amount =view.findViewById(R.id.amount);
            date = view.findViewById(R.id.date);
            relativeLayout = view.findViewById(R.id.relative_layout);



        }
    }




}


