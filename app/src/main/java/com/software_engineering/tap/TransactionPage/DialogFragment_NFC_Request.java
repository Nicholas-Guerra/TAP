package com.software_engineering.tap.TransactionPage;

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

import com.software_engineering.tap.Main_Notifications_Settings.Listener;
import com.software_engineering.tap.Main_Notifications_Settings.MainActivity;
import com.software_engineering.tap.R;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class DialogFragment_NFC_Request extends DialogFragment {

    View rootView;
    TextView title;
    ImageView close;
    double amount;
    private Listener mListener;


    public DialogFragment_NFC_Request() {
        // Required empty public constructor
    }

    public static DialogFragment_NFC_Request newInstance(double amount) {
        Bundle bundle = new Bundle();
        bundle.putDouble("Amount", amount);

        DialogFragment_NFC_Request fragment = new DialogFragment_NFC_Request();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dialog_fragment_nfc, container, false);
        title = rootView.findViewById(R.id.title);
        amount = getArguments().getDouble("Amount");
        title.setText(String.valueOf(amount) + " TPC");


        mListener = (MainActivity) getActivity();
        mListener.onDialogDisplayed(true);




        setCancelable(false);

        close = rootView.findViewById(R.id.close_button);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return rootView;
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener.onDialogDismissed();
    }




    public NdefMessage onNfcDetected(){

        byte[] payload = MainActivity.getUser().userName.
                getBytes(Charset.forName("UTF-8"));

        NdefRecord record = new NdefRecord(
                NdefRecord.TNF_WELL_KNOWN,  //Our 3-bit Type name format
                NdefRecord.RTD_TEXT,        //Description of our payload
                new byte[0],                //The optional id for our Record
                payload);

        byte[] payload2 = new byte[8];
        ByteBuffer.wrap(payload2).putDouble(amount);

        NdefRecord record2 = new NdefRecord(
                NdefRecord.TNF_WELL_KNOWN,  //Our 3-bit Type name format
                NdefRecord.RTD_TEXT,        //Description of our payload
                new byte[0],                //The optional id for our Record
                payload2);

        dismiss();

        return new NdefMessage(new NdefRecord[]{record, record2});
    }

}