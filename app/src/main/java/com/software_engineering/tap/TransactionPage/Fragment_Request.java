package com.software_engineering.tap.TransactionPage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.software_engineering.tap.R;

public class Fragment_Request extends Fragment {

    View rootView;


    public Fragment_Request() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_request, container, false);

        return rootView;
    }
}