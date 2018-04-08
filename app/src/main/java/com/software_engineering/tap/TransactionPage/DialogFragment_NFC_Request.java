package com.software_engineering.tap.TransactionPage;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.software_engineering.tap.R;

public class DialogFragment_NFC_Request extends DialogFragment {

    View rootView;
    TextView title;
    ImageView close;
    ProgressBar timer;
    double amount;


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
        rootView = inflater.inflate(R.layout.dialog_fragment_nfc_request, container, false);
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
}