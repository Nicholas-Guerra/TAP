package com.software_engineering.tap.AccountPage;


import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.software_engineering.tap.Main_Notifications_Settings.MainActivity;
import com.software_engineering.tap.R;
import com.software_engineering.tap.TransactionPage.sendToServer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Fragment_Account extends Fragment implements View.OnClickListener {

    ImageView refresh;
    TextView vwMore;
    List<Transaction> recentTransactions;
    TextView frsttofromName;
    TextView frsttransAmount;
    TextView frsttransStatus;

    //Whole block sets up names to be used for the sencond recent transactions
    TextView sndtofromName;
    TextView sndTransAmount;
    TextView sndtransStatus;

    //Whole block sets up names to be used for the third recent transactions
    TextView thrdtofromName;
    TextView thrdTransAmount;
    TextView thrdtransStatus;
    TextView nameText;
    TextView balanceText;


    User user;


    public Fragment_Account() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        user = MainActivity.getUser();

        refresh = rootView.findViewById(R.id.refresh);
        refresh.setOnClickListener(this);

        vwMore = rootView.findViewById(R.id.viewMore);
        vwMore.setOnClickListener(this);

        nameText = rootView.findViewById(R.id.user);
        nameText.setText(user.userName); //setting username on account page to firstName

        balanceText = rootView.findViewById(R.id.actualBalance);


        //Whole block sets up names to be used for the first recent transactions
        frsttofromName = rootView.findViewById(R.id.fstTransName);
        frsttransAmount = rootView.findViewById(R.id.fstTransAmount);
        frsttransStatus = rootView.findViewById(R.id.fstTransStatus);

        //Whole block sets up names to be used for the sencond recent transactions
        sndtofromName = rootView.findViewById(R.id.sndTransName);
        sndTransAmount = rootView.findViewById(R.id.sndTransAmount);
        sndtransStatus = rootView.findViewById(R.id.sndTransStatus);

        //Whole block sets up names to be used for the third recent transactions
        thrdtofromName = rootView.findViewById(R.id.trdTransName);
        thrdTransAmount = rootView.findViewById(R.id.trdTransAmount);
        thrdtransStatus = rootView.findViewById(R.id.trdTransStatus);

        //refresh();

        return rootView;



    }



    @SuppressLint("StaticFieldLeak")
    @Override
    public void onClick(View v){
        if(v == refresh){
            refresh();
        } else if(v == vwMore){
           new DialogFragment_ViewMoreTransactions().show(getFragmentManager(), "DialogViewMore");
        }

    }



  @SuppressLint("StaticFieldLeak")
  private void refresh(){
      try {
          JSONObject object = new JSONObject();
          object.put("Request", "History")
                  .put("userName", MainActivity.getUser().userName);

          new sendToServer(getContext(), true, "Updating", object){
              @Override
              public void onPostExecute(final JSONObject receivedJSON) {
                  super.onPostExecute(receivedJSON);
                          updateDatabaseTransactions(receivedJSON);
              }
          }.execute();


      } catch (JSONException e) {
          e.printStackTrace();
      }
  }

  private void updateDatabaseTransactions(final JSONObject receivedJSON){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String status = receivedJSON.getString("Status");
                    if(status.equals("Complete")){

                        MainActivity.getUser().balance = receivedJSON.getDouble("balance");
                        AppDatabase.getInstance(getContext()).userDao().update(MainActivity.getUser());

                        AppDatabase.getInstance(getContext()).transactionDao().deleteALL();

                        JSONArray array = receivedJSON.getJSONArray("array");
                        JSONObject transaction;
                        for (int i=0; i < array.length(); i++) {
                            transaction = array.getJSONObject(i);

                            String id = transaction.getString("transactionID");
                            String to_from = transaction.getString("to_from");
                            String stat = transaction.getString("status");
                            double amount = transaction.getDouble("amount");
                            long time = transaction.getLong("time");

                            Transaction entry = new Transaction(to_from, amount, stat, time, id);
                            AppDatabase.getInstance(getContext()).transactionDao().insert(entry);

                            user = MainActivity.getUser();
                            balanceText.setText(String.valueOf(user.balance));
                            refreshRecent();

                        }

                    } else{
                        Toast.makeText(getContext(), receivedJSON.getString("Message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
  }

  private void refreshRecent(){
              int i = 0;
              recentTransactions = AppDatabase.getInstance(getContext()).transactionDao().getRecent();
              for(Transaction transaction: recentTransactions) {
                  if (i == 0) {
                      frsttofromName.setText(transaction.toFromName);
                      frsttransAmount.setText(String.valueOf(transaction.amount));
                      if (transaction.amount > 0) {
                          frsttransAmount.setTextColor(Fragment_Account.this.getResources().getColor(R.color.colorDeposit));
                      } else {
                          frsttransAmount.setTextColor(Fragment_Account.this.getResources().getColor(R.color.colorDeduct));
                      }
                      frsttransStatus.setText(transaction.status);
                  } else if (i == 1) {
                      sndtofromName.setText(transaction.toFromName);
                      sndTransAmount.setText(String.valueOf(transaction.amount));
                      if (transaction.amount > 0) {
                          sndTransAmount.setTextColor(Fragment_Account.this.getResources().getColor(R.color.colorDeposit));
                      } else {
                          sndTransAmount.setTextColor(Fragment_Account.this.getResources().getColor(R.color.colorDeduct));
                      }
                      sndtransStatus.setText(transaction.status);
                  } else if (i == 2) {
                      thrdtofromName.setText(transaction.toFromName);
                      thrdTransAmount.setText(String.valueOf(transaction.amount));
                      if (transaction.amount > 0) {
                          thrdTransAmount.setTextColor(Fragment_Account.this.getResources().getColor(R.color.colorDeposit));
                      } else {
                          thrdTransAmount.setTextColor(Fragment_Account.this.getResources().getColor(R.color.colorDeduct));
                      }
                      thrdtransStatus.setText(transaction.status);
                  }
                  i++;
              }
  }

}


