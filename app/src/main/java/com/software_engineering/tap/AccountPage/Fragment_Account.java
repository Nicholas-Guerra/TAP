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

   // SwipeRefreshLayout swipeRefreshLayout;


    public Fragment_Account() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        refresh = rootView.findViewById(R.id.refresh);
        refresh.setOnClickListener(this);

        vwMore = rootView.findViewById(R.id.viewMore);
        vwMore.setOnClickListener(this);

        TextView nameText = rootView.findViewById(R.id.user);
        User user = MainActivity.getUser();
        nameText.setText(user.firstName); //setting username on account page to firstName

        TextView balanceText = rootView.findViewById(R.id.actualBalance);
        balanceText.setText(String.valueOf(user.balance));

        //Whole block sets up names to be used for the first recent transactions
        TextView frsttofromName = rootView.findViewById(R.id.fstTransName);
        TextView frsttransAmount = rootView.findViewById(R.id.fstTransAmount);
        TextView frsttransStatus = rootView.findViewById(R.id.fstTransStatus);

        //Whole block sets up names to be used for the sencond recent transactions
        TextView sndtofromName = rootView.findViewById(R.id.sndTransName);
        TextView sndTransAmount = rootView.findViewById(R.id.sndTransAmount);
        TextView sndtransStatus = rootView.findViewById(R.id.sndTransStatus);

        //Whole block sets up names to be used for the third recent transactions
        TextView thrdtofromName = rootView.findViewById(R.id.trdTransName);
        TextView thrdTransAmount = rootView.findViewById(R.id.trdTransAmount);
        TextView thrdtransStatus = rootView.findViewById(R.id.trdTransStatus);

        recentTransactions = AppDatabase.getInstance(getContext()).transactionDao().getRecent();
        for(Transaction transaction: recentTransactions){
            int i = 0;
            if(i == 0) {
                frsttofromName.setText(transaction.toFromName);
                frsttransAmount.setText(String.valueOf(transaction.amount));
                if (transaction.amount > 0) {
                    frsttransAmount.setTextColor(this.getResources().getColor(R.color.colorDeposit));
                }
                else{
                    frsttransAmount.setTextColor(this.getResources().getColor(R.color.colorDeduct));
                }
                frsttransStatus.setText(transaction.status);
            }
            else if(i == 1){
                sndtofromName.setText(transaction.toFromName);
                sndTransAmount.setText(String.valueOf(transaction.amount));
                if(transaction.amount > 0 ){
                    sndTransAmount.setTextColor(this.getResources().getColor(R.color.colorDeposit));
                }
                else{
                    sndTransAmount.setTextColor(this.getResources().getColor(R.color.colorDeduct));
                }
                sndtransStatus.setText(transaction.status);
            }
            else if (i == 2) {
                thrdtofromName.setText(transaction.toFromName);
                thrdTransAmount.setText(String.valueOf(transaction.amount));
                if(transaction.amount > 0 ){
                    thrdTransAmount.setTextColor(this.getResources().getColor(R.color.colorDeposit));
                }
                else{
                    thrdTransAmount.setTextColor(this.getResources().getColor(R.color.colorDeduct));
                }
                thrdtransStatus.setText(transaction.status);
            }
            i++;
        }





       AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                AppDatabase.getInstance(getContext()).transactionDao().insert(new Transaction("dad", 28.1, "Pending", 012516, "010101"));

            }
        });

        return rootView;



    }



    @SuppressLint("StaticFieldLeak")
    @Override
    public void onClick(View v){
        if(v == refresh){
            final JSONObject obj = new JSONObject();
            User user = AppDatabase.getInstance(getContext()).userDao().getUser();
            try {
                obj.put("Request", "TransactionUpdate");
                obj.put("userName", user.userName);

                new sendToServer(getActivity(), true,"Connecting", obj) {
                    @Override
                    public void onPostExecute(JSONObject receivedJSON) {
                        super.onPostExecute(receivedJSON);

                        try {
                            JSONArray array = receivedJSON.getJSONArray("array");
                            for(int x = 0; x <= array.length(); x++){
                                JSONObject object = array.getJSONObject(x);
                                Transaction transaction = new Transaction(object.getString("to_from"),
                                        object.getDouble("amount"), object.getString("status"),object.getLong("time"),
                                        object.getString("transactionID"));

                                // AppDatabase.getInstance(getContext()).transactionDao().updateTransaction(transaction);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }.execute();

            } catch (JSONException e) {
                e.printStackTrace();
            }


        } else if(v == vwMore){
           new DialogFragment_ViewMoreTransactions().show(getFragmentManager(), "DialogViewMore");
        }


    }



  /* public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       swipeRefreshLayout = swipeRefreshLayout.findViewById(R.id.swipe);
       (R.id.fragment_account);
       swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
           @Override
           public void onRefresh() {
           }
       });
   }*/

}


