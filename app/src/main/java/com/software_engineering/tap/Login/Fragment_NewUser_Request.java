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









        return rootView;
    }


}
