package com.software_engineering.tap.AccountPage;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.software_engineering.tap.Main_Notifications_Settings.MainActivity;
import com.software_engineering.tap.Main_Notifications_Settings.RecyclerViewAdpater_Notifications;
import com.software_engineering.tap.Main_Notifications_Settings.Transaction_Notification;
import com.software_engineering.tap.Main_Notifications_Settings.ViewModel_Transaction_Notification;
import com.software_engineering.tap.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by grant_000 on 4/11/2018.
 */

public class DialogFragment_ViewMoreTransactions extends DialogFragment {

    View rootView;
    RecyclerView recyclerView;
    ImageView close;

    public DialogFragment_ViewMoreTransactions() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dialog_fragment_viewmoretransactions, container, false);


        recyclerView = rootView.findViewById(R.id.trv);

        close = rootView.findViewById(R.id.viewMore_close_button);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                dismiss();
            }
        });


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                ViewMore_Adapter recyclerViewAdpater = new ViewMore_Adapter(getActivity(), AppDatabase.getInstance(getContext()).transactionDao().getAll());
                recyclerView.setAdapter(recyclerViewAdpater);
            }
        });

        return rootView;
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