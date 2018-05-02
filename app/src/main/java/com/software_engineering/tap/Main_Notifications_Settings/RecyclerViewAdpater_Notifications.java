package com.software_engineering.tap.Main_Notifications_Settings;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.software_engineering.tap.AccountPage.AppDatabase;
import com.software_engineering.tap.R;
import com.software_engineering.tap.TransactionPage.DialogFragment_Authentication;
import com.software_engineering.tap.TransactionPage.sendToServer;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RecyclerViewAdpater_Notifications  extends RecyclerView.Adapter<RecyclerViewAdpater_Notifications.RecyclerViewHolder> {


    private List<Transaction_Notification> transactionNotificationList;
    private DateFormat df = new SimpleDateFormat("EEE MM/dd/yy hh:mm aaa");
    private Context context;

    public RecyclerViewAdpater_Notifications(Context context, List<Transaction_Notification> transaction_notificationList) {
        transactionNotificationList = transaction_notificationList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewmodel_transaction_notification, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        holder.toName.setText(transactionNotificationList.get(position).toName);
        holder.amount.setText(String.valueOf(transactionNotificationList.get(position).amount));
        holder.date.setText(df.format(new Date(transactionNotificationList.get(position).date)));
        holder.transactionID = transactionNotificationList.get(position).transactionID;


        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Confirm");
                builder.setMessage("Send " + holder.toName.getText() + " " + holder.amount.getText() + " TPC?");
                builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final boolean[] availableFunds = {false};
                        try {
                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    if(AppDatabase.getInstance(context).userDao().getUser().balance < Double.valueOf(holder.amount.getText().toString())){
                                        availableFunds[0] = false;
                                    } else{
                                        availableFunds[0] = true;
                                    }
                                }
                            });

                            thread.start();
                            thread.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if(!availableFunds[0]) {
                            Toast.makeText(context, "Insufficient funds", Toast.LENGTH_LONG).show();
                            return;
                        }
                        final DialogFragment_Authentication dialogFragment_authentication = new DialogFragment_Authentication();
                        dialogFragment_authentication.show(((AppCompatActivity)context).getSupportFragmentManager(), "authentication");
                        ((AppCompatActivity)context).getSupportFragmentManager().executePendingTransactions();

                        dialogFragment_authentication.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @SuppressLint("StaticFieldLeak")
                            @Override
                            public void onDismiss(DialogInterface dialogInterface) {
                                if (dialogFragment_authentication.getSuccess()) {
                                    Toast.makeText(context, "Accepted", Toast.LENGTH_SHORT).show();
                                    //Authentication successful

                                    JSONObject object = new JSONObject();
                                    try {
                                        object.put("Request", "Transaction")
                                                .put("sender", MainActivity.getUser().userName)
                                                .put("receiver", holder.toName.getText().toString())
                                                .put("amount", Double.valueOf(holder.amount.getText().toString()))
                                                .put("transactionID", holder.transactionID);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    new sendToServer(context,true, "Verifying", object) {
                                        @Override
                                        public void onPostExecute(final JSONObject receivedJSON) {
                                            super.onPostExecute(receivedJSON);
                                            try {
                                                String status = receivedJSON.getString("Status");
                                                if(status.equals("Complete")){
                                                    Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();
                                                    AsyncTask.execute(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            AppDatabase.getInstance(context).transaction_notificationDao().delete(transactionNotificationList.get(holder.getAdapterPosition()));
                                                        }
                                                    });
                                                } else{
                                                    String message = receivedJSON.getString("Message");
                                                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                                                }


                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }.execute();


                                } else {
                                    Toast.makeText(context, "Canceled", Toast.LENGTH_SHORT).show();
                                    //Authentication canceled
                                }
                            }
                        });

                        dialogInterface.dismiss();
                    }
                });


                builder.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {



                        final DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @SuppressLint("StaticFieldLeak")
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        AsyncTask.execute(new Runnable() {
                                            @Override
                                            public void run() {
                                                AppDatabase.getInstance(context).transaction_notificationDao().delete(transactionNotificationList.get(holder.getAdapterPosition()));
                                            }
                                        });


                                        JSONObject object = new JSONObject();
                                        try {
                                            object.put("Request", "decline");
                                            object.put("transactionID", transactionNotificationList.get(position).transactionID);
                                            new sendToServer(context,false, null, object) {
                                                @Override
                                                public void onPostExecute(final JSONObject receivedJSON) {
                                                    super.onPostExecute(receivedJSON);

                                                    try {
                                                        String status = receivedJSON.getString("Status");
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }


                                                }
                                            }.execute();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                        break;
                                    case DialogInterface.BUTTON_NEGATIVE:
                                        //No button clicked
                                        break;
                                }
                            }
                        };

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                                .setNegativeButton("No", dialogClickListener).show();

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
        private String transactionID;


        RecyclerViewHolder(View view) {
            super(view);

            toName = view.findViewById(R.id.toName);
            amount =view.findViewById(R.id.amount);
            date = view.findViewById(R.id.date);
            relativeLayout = view.findViewById(R.id.relative_layout);

        }
    }





}


