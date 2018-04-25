package com.software_engineering.tap.Login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.DialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;

import com.software_engineering.tap.Main_Notifications_Settings.MainActivity;
import com.software_engineering.tap.R;
import com.software_engineering.tap.TransactionPage.sendToServer;

import org.json.JSONException;
import org.json.JSONObject;


public class Fragment_NewUser_Request extends DialogFragment{

    View rootView;
    TextView Title;
    Button submit;
    EditText et1, et2, et3, et4, et5, et6, et7;


    public Fragment_NewUser_Request(){
        //Constructor Needed
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dialog_fragment_newuser_request, container, false);

        submit = (Button) rootView.findViewById(R.id.Submit);
        et1 = (EditText) rootView.findViewById(R.id.New_UserEdit);
        et2 = (EditText) rootView.findViewById(R.id.New_FirstEdit);
        et3 = (EditText) rootView.findViewById(R.id.New_LastEdit);
        et4 = (EditText) rootView.findViewById(R.id.New_PhoneEdit);
        et5 = (EditText) rootView.findViewById(R.id.New_EditEmail);
        et6 = (EditText) rootView.findViewById(R.id.New_PassEdit);
        et7 = (EditText) rootView.findViewById(R.id.New_PinEdit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newuser(et1.getText().toString(), et2.getText().toString(), et3.getText().toString(), et4.getText().toString(), et5.getText().toString(),
                        et6.getText().toString(), et7.getText().toString());

            }
        });


        return rootView;
    }


    @SuppressLint("StaticFieldLeak")
    private void newuser(final String et1, final String et2, final String et3, final String et4, final String et5, final String et6, final String et7){



    }

}
