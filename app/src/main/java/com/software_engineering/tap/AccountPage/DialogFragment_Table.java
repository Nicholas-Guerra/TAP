package com.software_engineering.tap.AccountPage;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.software_engineering.tap.AccountPage.AppDatabase;
import com.software_engineering.tap.Main_Notifications_Settings.Listener;
import com.software_engineering.tap.Main_Notifications_Settings.MainActivity;
import com.software_engineering.tap.R;

import java.io.IOException;
import java.lang.annotation.Inherited;
import java.nio.charset.Charset;

//**Created by Michael 20180425*
//**Modified by Michael 20180429*

public class DialogFragment_Table {

    View rootView;
    TextView table;
    ImageView close;
    private Listener mListener;

    public DialogFragment_Table(){
        // Required empty public constructor
    }

    @Override
    public View onCreatedView(LayoutInflater inflater, View container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.dialog_fragment_viewmoretransactions, container, false);
        table = rootView(R.id.table);
        table.setText("Recent Transactions");

        close = rootView(R.id.close_button);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        return  rootView;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mListener = (MainActivity)context;
        mListener.onDialogDisplayed(true);
    }

    @Override
    public void OnDetach(){
        super.OnDetach();
        mListener.onDialogDismissed();
    }




}
