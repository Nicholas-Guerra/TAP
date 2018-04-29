package com.software_engineering.tap.AccountPage;

import android.app.DialogFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.software_engineering.tap.Main_Notifications_Settings.MainActivity;
import com.software_engineering.tap.Main_Notifications_Settings.RecyclerViewAdpater_Notifications;
import com.software_engineering.tap.Main_Notifications_Settings.Transaction_Notification;
import com.software_engineering.tap.Main_Notifications_Settings.ViewModel_Transaction_Notification;
import com.software_engineering.tap.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by grant_000 on 4/11/2018.
 */

public class DialogFragment_ViewMoreTransactions extends DialogFragment {

    View rootView;
    RecyclerView recyclerView;

    public DialogFragment_ViewMoreTransactions() {
        // Required empty public constructor
    }


    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dialog_fragment_viewmoretransactions, container, false);
        recyclerView = rootView.findViewById(R.id.trv);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final ViewMore_Adapter recyclerViewAdpater = new ViewMore_Adapter(getActivity(), new ArrayList<Transaction>());
        recyclerView.setAdapter(recyclerViewAdpater);
        ViewModel_ViewMore_Transaction viewModel = ViewModelProviders.of((MainActivity) getActivity()).get(ViewModel_ViewMore_Transaction.class);

        viewModel.getTransactionNotifications().observe((MainActivity) getActivity(), new Observer<List<Transaction>>() {
            @Override
            public void onChanged(@Nullable List<Transaction> transactionList) {
                recyclerViewAdpater.addItems(transactionList);
            }
        });


        return rootView;
    }*/
}