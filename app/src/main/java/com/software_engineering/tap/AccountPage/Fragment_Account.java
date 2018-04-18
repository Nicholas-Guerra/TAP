package com.software_engineering.tap.AccountPage;


import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
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

import java.util.Date;
import java.util.UUID;

public class Fragment_Account extends Fragment implements View.OnClickListener {

    ImageView refresh;


    public Fragment_Account() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        refresh = rootView.findViewById(R.id.refresh);
        refresh.setOnClickListener(this);

        //TextView nameText = rootView.findViewById(R.id.name);

        //User user = MainActivity.getDb().userDao().getUser();

        //User user = AppDatabase.getInstance(getContext()).userDao().getUser();
        //String username = user.userName;

        //nameText.setText(user.firstName);

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
                                Transaction transaction = new Transaction(object.getString("to_from"),  object.getDouble("amount"), object.getString("status"),object.getLong("time"), object.getString("transactionID"));

                                //AppDatabase.getInstance(getContext()).transactionDao().updateTransaction(transaction);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }.execute();

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


    }

}


