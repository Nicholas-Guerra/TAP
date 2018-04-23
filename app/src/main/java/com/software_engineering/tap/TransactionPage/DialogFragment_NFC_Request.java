package com.software_engineering.tap.TransactionPage;

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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.software_engineering.tap.Main_Notifications_Settings.Listener;
import com.software_engineering.tap.Main_Notifications_Settings.MainActivity;
import com.software_engineering.tap.R;

import java.io.IOException;

public class DialogFragment_NFC_Request extends DialogFragment {

    View rootView;
    TextView title;
    ImageView close;
    ProgressBar timer;
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
    public void onStart() {
        super.onStart();
        Dialog d = getDialog();
        if (d!=null){
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            d.getWindow().setLayout(width, height);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dialog_fragment_nfc, container, false);
        title = rootView.findViewById(R.id.title);
        amount = getArguments().getDouble("Amount");
        title.setText(String.valueOf(amount) + " TPC");
        timer =  rootView.findViewById(R.id.progressBar);

        new CountDownTimer(10000, 100) {

            public void onTick(long millisUntilFinished) {
                timer.setProgress((int) ((10000 - millisUntilFinished)/100));
            }
            public void onFinish() {
                dismiss();
            }
        }.start();

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
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (MainActivity)context;
        mListener.onDialogDisplayed();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener.onDialogDismissed();
    }

    public void onNfcDetected(Ndef ndef){

        readFromNFC(ndef);
    }

    private void readFromNFC(Ndef ndef) {

        try {
            ndef.connect();
            NdefMessage ndefMessage = ndef.getNdefMessage();
            String message = new String(ndefMessage.getRecords()[0].getPayload());
            //Log.d(TAG, "readFromNFC: "+message);
            //mTvMessage.setText(message);
            ndef.close();

        } catch (IOException | FormatException e) {
            e.printStackTrace();

        }
    }

}