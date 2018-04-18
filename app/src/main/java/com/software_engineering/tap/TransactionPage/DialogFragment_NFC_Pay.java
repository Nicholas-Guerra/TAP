package com.software_engineering.tap.TransactionPage;

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

import com.software_engineering.tap.AccountPage.AppDatabase;
import com.software_engineering.tap.Main_Notifications_Settings.Listener;
import com.software_engineering.tap.Main_Notifications_Settings.MainActivity;
import com.software_engineering.tap.R;

import java.io.IOException;
import java.nio.charset.Charset;

public class DialogFragment_NFC_Pay extends DialogFragment {

    View rootView;
    TextView title;
    ImageView close;
    ProgressBar timer;
    private Listener mListener;
    String user;


    public DialogFragment_NFC_Pay() {
        // Required empty public constructor
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
        title.setText("Pay");
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

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                user = AppDatabase.getInstance(getContext()).userDao().getUser().userName;
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


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
        writeToNfc(ndef);
    }

    private void writeToNfc(Ndef ndef){

        if (ndef != null) {

            try {
                ndef.connect();
                NdefRecord mimeRecord = NdefRecord.createMime("text/plain", user.getBytes(Charset.forName("US-ASCII")));
                ndef.writeNdefMessage(new NdefMessage(mimeRecord));
                ndef.close();
                //Write Successful
                //mTvMessage.setText(getString(R.string.message_write_success));

            } catch (IOException | FormatException e) {
                e.printStackTrace();
                //mTvMessage.setText(getString(R.string.message_write_error));

            } finally {
                //mProgress.setVisibility(View.GONE);
            }

        }
    }


}