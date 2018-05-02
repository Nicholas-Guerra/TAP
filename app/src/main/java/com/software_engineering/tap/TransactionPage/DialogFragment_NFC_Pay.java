package com.software_engineering.tap.TransactionPage;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.CpuUsageInfo;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;

public class DialogFragment_NFC_Pay extends DialogFragment {

    View rootView;
    TextView title;
    ImageView close;
    private Listener mListener;


    public DialogFragment_NFC_Pay() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dialog_fragment_nfc, container, false);
        title = rootView.findViewById(R.id.title);
        title.setText("Pay");

        close = rootView.findViewById(R.id.close_button);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        setCancelable(false);

        new CountDownTimer(10000, 100) {
            public void onTick(long millisUntilFinished) { }
            public void onFinish() {
                dismiss();
            }
        }.start();

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (MainActivity)context;
        mListener.onDialogDisplayed(false);
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        mListener.onDialogDismissed();
    }



    @SuppressLint("StaticFieldLeak")
    public void onNfcDetected(String receiver, double amount){
        JSONObject object = new JSONObject();
        try {
            object.put("Request", "Transaction")
                    .put("sender", MainActivity.getUser().userName)
                    .put("receiver", receiver)
                    .put("amount", amount)
                    .put("transactionID", "nfc");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new sendToServer(getContext(),true, "Verifying", object) {
            @Override
            public void onPostExecute(final JSONObject receivedJSON) {
                super.onPostExecute(receivedJSON);
                try {
                    String status = receivedJSON.getString("Status");
                    if(status.equals("Complete")){
                        mListener.showToast("Success");
                    } else{
                        String message = receivedJSON.getString("Message");
                        mListener.showToast(message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }.execute();


        dismiss();
    }
}