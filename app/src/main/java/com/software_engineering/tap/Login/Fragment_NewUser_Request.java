package com.software_engineering.tap.Login;

import android.app.Dialog;
import android.content.Context;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Button;
import com.software_engineering.tap.R;

import com.software_engineering.tap.Main_Notifications_Settings.Listener;
import com.software_engineering.tap.Main_Notifications_Settings.MainActivity;
//import com.software_engineering.tap.R;

public class Fragment_NewUser_Request extends DialogFragment {

    View rootView;
    TextView Title, tv1, tv2, tv3, tv4, tv5, tv6, tv7;
    Button button;
    EditText et1, et2, et3, et4, et5, et6, et7;
    CheckBox cb1, cb2;
    RadioButton rb;

    public Fragment_NewUser_Request(){
        //Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dialog_fragment_newuser_request, container, false);

        Title = rootView.findViewById(R.id.Title);
        button = rootView.findViewById(R.id.Submit);

        tv1 = rootView.findViewById(R.id.New_FirstText);
        tv2 = rootView.findViewById(R.id.New_UserText);
        tv3 = rootView.findViewById(R.id.New_PhoneText);
        tv4 = rootView.findViewById(R.id.New_LastName);
        tv5 = rootView.findViewById(R.id.New_EmailText);
        tv6 = rootView.findViewById(R.id.New_PassText);
        tv7 = rootView.findViewById(R.id.New_PinText);

        et1 = rootView.findViewById(R.id.New_FirstEdit);
        et2 = rootView.findViewById(R.id.New_UserEdit);
        et3 = rootView.findViewById(R.id.New_PhoneEdit);
        et4 = rootView.findViewById(R.id.New_EditEmail);
        et5 = rootView.findViewById(R.id.New_PassText);
        et6 = rootView.findViewById(R.id.New_PinEdit);
        et7 = rootView.findViewById(R.id.New_LastEdit);

        cb1 = rootView.findViewById(R.id.user_kiosk2);
        cb2 = rootView.findViewById(R.id.user_cust);

        rb = rootView.findViewById(R.id.Fingerprint);



        return rootView;
    }
}
