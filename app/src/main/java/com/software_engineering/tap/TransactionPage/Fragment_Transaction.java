package com.software_engineering.tap.TransactionPage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.software_engineering.tap.R;

public class Fragment_Transaction extends Fragment implements View.OnClickListener{

    View rootView;
    FragmentManager fragmentManager;
    TextView request, pay;
    int orange, white;

    public Fragment_Transaction() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_transaction, container, false);
        fragmentManager = getFragmentManager();

        request = (TextView) rootView.findViewById(R.id.request_text);
        pay = (TextView) rootView.findViewById(R.id.pay_text);

        request.setOnClickListener(this);
        pay.setOnClickListener(this);

        orange = getResources().getColor(R.color.colorTapOrange);
        white = getResources().getColor(R.color.colorPrimaryLight);

        fragmentManager.beginTransaction().replace(R.id.fragment_container, new Fragment_Request()).commit();

        return rootView;
    }

    @Override
    public void onClick(View v){
        if(v == request){
            fragmentManager.beginTransaction().replace(R.id.fragment_container, new Fragment_Request()).commit();
            request.setTextColor(orange);
            pay.setTextColor(white);
        } else if(v == pay){
            fragmentManager.beginTransaction().replace(R.id.fragment_container, new Fragment_Pay()).commit();
            request.setTextColor(white);
            pay.setTextColor(orange);
        }
    }
}
