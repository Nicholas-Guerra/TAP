package com.software_engineering.tap.Main_Notifications_Settings;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.software_engineering.tap.R;

/**
 * Created by grant_000 on 5/1/2018.
 */



public class DialogFragment_Help extends DialogFragment {
    View mView;
    TextView textHelp;
    ImageView close;

    public DialogFragment_Help() {
        //Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.dialog_fragment_help, container, false);

        textHelp = mView.findViewById(R.id.helpInfo);
        textHelp.setText(Html.fromHtml(getString(R.string.Help)));

        close = mView.findViewById(R.id.help_close_button);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                dismiss();
            }
        });

        return mView;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }
}
