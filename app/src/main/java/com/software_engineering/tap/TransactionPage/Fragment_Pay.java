package com.software_engineering.tap.TransactionPage;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.software_engineering.tap.Main_Notifications_Settings.MainActivity;
import com.software_engineering.tap.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Fragment_Pay extends Fragment implements View.OnClickListener{

    View rootView;
    TextView notifications;
    ImageView btn_nfc;
    Button connect;


    public Fragment_Pay() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_pay, container, false);

        notifications = rootView.findViewById(R.id.notification_num);
        notifications.setOnClickListener(this);

        btn_nfc = rootView.findViewById(R.id.btn_nfc);
        btn_nfc.setOnClickListener(this);

        connect = rootView.findViewById(R.id.connection);
        connect.setOnClickListener(this);


        return rootView;
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void onClick(View v){
        if(v == notifications){
            MainActivity.openDrawer();
        } else if(v == btn_nfc){

        } else if(v == connect){
            try {


                JSONObject send = new JSONObject();
                send.put("Request", "Transaction");
                send.put("Sender", "Bob");
                send.put("Receiver", "Sue");

                new sendToServer(getActivity(), true,"Connecting", send) {
                    @Override
                    public void onPostExecute(JSONObject receivedJSON) {
                        super.onPostExecute(receivedJSON);

                        Toast.makeText(getContext(), receivedJSON.toString(), Toast.LENGTH_LONG).show();

                    }
                }.execute();


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


}