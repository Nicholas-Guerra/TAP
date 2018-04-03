package com.software_engineering.tap.TransactionPage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.software_engineering.tap.R;

public class Fragment_Transaction extends Fragment {


    public Fragment_Transaction() {
        // Required empty public constructor
        String bob;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tap, container, false);
    }
}
