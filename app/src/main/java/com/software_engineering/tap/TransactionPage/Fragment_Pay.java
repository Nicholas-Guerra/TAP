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
                        new DialogFragment_NFC_Pay().show(getFragmentManager().beginTransaction(), "nfc_pay");


                    } else{
                        Toast.makeText(getContext(), "Canceled", Toast.LENGTH_SHORT).show();
                        //Authentication canceled

                    }
                }
            });
        }
    }


}