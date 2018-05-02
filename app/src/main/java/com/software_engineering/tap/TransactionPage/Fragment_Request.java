package com.software_engineering.tap.TransactionPage;


import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.software_engineering.tap.AccountPage.AppDatabase;
import com.software_engineering.tap.ExplorePage.returnRates;
import com.software_engineering.tap.R;

public class Fragment_Request extends Fragment implements View.OnClickListener{

    View rootView;
    FragmentManager fragmentManager;
    TextView TAPtext, conversionText;
    Button tap, send, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    ImageButton btnDEL, btnCLR;
    int total = 0;
    double conversionRate;
    String conversionSymbol;
    Toast toast;


    public Fragment_Request() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_request, container, false);
        fragmentManager = getFragmentManager();

        TAPtext = rootView.findViewById(R.id.tapCoin);
        conversionText = rootView.findViewById(R.id.conversion);
        conversionText.setOnClickListener(this);

        tap = rootView.findViewById(R.id.tap);
        send = rootView.findViewById(R.id.send);

        tap.setOnClickListener(this);
        send.setOnClickListener(this);

        btn0 = rootView.findViewById(R.id.btn_0);
        btn1 = rootView.findViewById(R.id.btn_1);
        btn2 = rootView.findViewById(R.id.btn_2);
        btn3 = rootView.findViewById(R.id.btn_3);
        btn4 = rootView.findViewById(R.id.btn_4);
        btn5 = rootView.findViewById(R.id.btn_5);
        btn6 = rootView.findViewById(R.id.btn_6);
        btn7 = rootView.findViewById(R.id.btn_7);
        btn8 = rootView.findViewById(R.id.btn_8);
        btn9 = rootView.findViewById(R.id.btn_9);
        btnDEL = rootView.findViewById(R.id.btn_back);
        btnCLR = rootView.findViewById(R.id.btn_clear);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnDEL.setOnClickListener(this);
        btnCLR.setOnClickListener(this);

        conversionRate = 1.1;
        conversionSymbol = "$";

        return rootView;
    }

    @Override
    public void onClick(View v){
        if(v == conversionText){


// Strings to Show In Dialog with Radio Buttons
            final CharSequence[] items = {"USD","BTC","ETH","LTC","BCH","EUR","GBP","JPY","CNY"};

            // Creating and Building the Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Select conversion rate");
            AlertDialog alertDialog;
            builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {

                    if(items[item].toString().equals("USD"))
                        conversionRate = 1.0;
                    else
                        conversionRate = new returnRates().rates(items[item].toString());
                    conversionSymbol = items[item].toString();
                    conversionText.setText(String.format("%.2f",(double)total / 100.0 * conversionRate) + conversionSymbol);

                    dialog.dismiss();
                }
            });
            alertDialog = builder.create();
            alertDialog.show();


        } else if(v == send){
            if(total != 0){
                DialogFragment_Send_Request.newInstance((double) total / 100.0).show(getFragmentManager().beginTransaction(), "send_request");
            } else{
                toast = Toast.makeText(getActivity(), "Nothing entered", Toast.LENGTH_SHORT);
                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 500);
            }
        } else if(v == tap){
            if(total!=0) {

                final DialogFragment_Authentication dialog = new DialogFragment_Authentication();
                dialog.show(getFragmentManager(), "authentication");
                getFragmentManager().executePendingTransactions();
                dialog.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        if(dialog.getSuccess()){
                            Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                            //Authentication successful
                            DialogFragment_NFC_Request.newInstance((double) total/ 100.0).show(getFragmentManager().beginTransaction(), "nfc_request");

                        } else{
                            Toast.makeText(getContext(), "Canceled", Toast.LENGTH_SHORT).show();
                            //Authentication canceled

                        }
                    }
                });




            } else {
                toast = Toast.makeText(getActivity(), "Nothing entered", Toast.LENGTH_SHORT);
                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 500);
            }
        }  else if(v == btnDEL){
            total /= 10;
        } else if(v == btnCLR){
            total = 0;
        } else if(String.format("%.2f",(double)total/100.0).length() >= 7) {
            toast = Toast.makeText(getActivity(), "Too long" , Toast.LENGTH_SHORT);
            toast.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 500);

        }else if(v == btn0){
            total *= 10;
        }  else if(v == btn1){
            total *= 10;
            total += 1;
        } else if(v == btn2){
            total *= 10;
            total += 2;
        } else if(v == btn3){
            total *= 10;
            total += 3;
        } else if(v == btn4){
            total *= 10;
            total += 4;
        } else if(v == btn5){
            total *= 10.0;
            total += 5;
        } else if(v == btn6){
            total *= 10;
            total += 6;
        } else if(v == btn7){
            total *= 10;
            total += 7;
        } else if(v == btn8){
            total *= 10;
            total += 8;
        } else if(v == btn9){
            total *= 10;
            total += 9;
        }


        TAPtext.setText(String.format("%.2f",(double)total/100.0));
        conversionText.setText(String.format("%.2f",(double)total / 100.0 * conversionRate) + conversionSymbol);
    }




}