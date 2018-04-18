package com.software_engineering.tap.AccountPage;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.software_engineering.tap.R;


/**
 * Created by grant_000 on 4/11/2018.
 */

public class DialogFragment_ViewMoreTransactions extends DialogFragment {

    View rootView;


    public DialogFragment_ViewMoreTransactions() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dialog_fragment_nfc, container, false);
        return rootView;
    }
}