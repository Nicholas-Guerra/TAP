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
import java.nio.charset.Charset;

//**Created by Michael 20180425*

public class DialogFragment_Table {

    View rootView;
    TextView title;
    ImageView close;
    private Listener mListener;

    public DialogFragment_Table(){
        // Required empty public constructor
    }

    //Still working on



}
