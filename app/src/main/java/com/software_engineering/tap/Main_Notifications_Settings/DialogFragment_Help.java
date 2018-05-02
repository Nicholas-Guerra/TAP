package com.software_engineering.tap.Main_Notifications_Settings;

import android.app.DialogFragment;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.software_engineering.tap.R;

/**
 * Created by grant_000 on 5/1/2018.
 */



public class DialogFragment_Help extends DialogFragment {
    View mView;
    TextView textHelp;

    public DialogFragment_Help() {
        //Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.dialog_fragment_help, container, false);
        textHelp = mView.findViewById(R.id.helpInfo);
        textHelp.setText(Html.fromHtml(getString(R.string.Help)));

        return mView;
    }
}
