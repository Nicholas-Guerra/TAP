package com.software_engineering.tap.TransactionPage;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.software_engineering.tap.Main_Notifications_Settings.MainActivity;
import com.software_engineering.tap.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Fragment_Pay extends Fragment implements View.OnClickListener{

    View rootView;
    TextView notifications;
    ImageView btn_nfc;
    Button connect;
    ProgressBar timer;


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

        timer = rootView.findViewById(R.id.progressBar);


        return rootView;
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void onClick(View v){
        if(v == notifications){
            MainActivity.openDrawer();
        } else if(v == btn_nfc){

            final DialogFragment_Authentication dialog = new DialogFragment_Authentication();
            dialog.show(getFragmentManager(), "authentication");
            getFragmentManager().executePendingTransactions();
            dialog.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    if(dialog.getSuccess()){
                        Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                        //Authentication successful


                    } else{
                        Toast.makeText(getContext(), "Canceled", Toast.LENGTH_SHORT).show();
                        //Authentication canceled

                    }
                }
            });



            btn_nfc.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.tap));
                timer.setProgress(1);
                btn_nfc.setOnClickListener(null);

                new receiveNFC(getActivity()).execute();

            new CountDownTimer(10000, 100) {

                public void onTick(long millisUntilFinished) {
                    timer.setProgress((int) ((10000 - millisUntilFinished)/100));
                }
                public void onFinish() {
                    btn_nfc.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.tap_dark));
                    timer.setProgress(100);
                    timer.setProgress(0);
                    btn_nfc.setOnClickListener(Fragment_Pay.this);
                }
            }.start();


        } else if(v == connect){
            /*try {


                /*JSONObject send = new JSONObject();
                send.put("Request", "Transaction");
                send.put("Sender", "Bob");
                send.put("Receiver", "Sue");

                new sendToServer(getActivity(), true,"Connecting", send) {
                    @Override
                    public void onPostExecute(JSONObject receivedJSON) {
                        super.onPostExecute(receivedJSON);


                    }
                }.execute();


            } catch (JSONException e) {
                e.printStackTrace();
            }*/


            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL myurl = new URL("https://fcm.googleapis.com/fcm/send");
                        HttpsURLConnection con = (HttpsURLConnection)myurl.openConnection();
                        con.setDoOutput(true);
                        con.setRequestMethod("POST");

                        con.setRequestProperty("Content-Type", "application/json");
                        con.setRequestProperty("Authorization", "key=AAAAvDSzk-s:APA91bEpIUE5NHy-axptjiaSxB5_waxCyH95UuXzw0HM_Sg7UR33pJRrc_AzQH5AxrS38BAqgVlD1Vfj3OeB4oce2IWwfMmMpOiauv9Ssvm32PzgkhAG-Wdu_PpJmCWVnGT7OD2fAjIq");

                        JSONObject object = new JSONObject();
                        JSONObject data = new JSONObject();
                        data.put("User Name", "Sally")
                                .put("Amount", "41.28")
                                .put("Date", "1523831301798");

                        object.put("to", "dx-5O1rtuD4:APA91bEWTuV2pRwzyFpG8tNK9U6Rj4rwmIq7eHrH6Y7Cgu4hkp5iSmWgJfoPMt3uOXbP1ku6zhONYfY6BKUIsHoNZ-nIugUoyF3nVP0Z-JD9ANRRA0nE5um9xpGlK0iUu4l6Zb7-IlAL")
                                .put("data", data);

                        OutputStream outputStream = con.getOutputStream();
                        outputStream.write(object.toString().getBytes());
                        outputStream.flush();

                        int responseCode = con.getResponseCode();
                        Log.i("PostRequest", "Sending 'POST' request");
                        Log.i("PostRequest", "Response Code : " + responseCode+" "+con.getResponseMessage());


                        outputStream.close();


                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();


                    }
                }
            }).start();

        }
    }


}