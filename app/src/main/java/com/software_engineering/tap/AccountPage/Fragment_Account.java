package com.software_engineering.tap.AccountPage;


import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.software_engineering.tap.R;

public class Fragment_Account extends Fragment {

    AppDatabase db = Room.databaseBuilder(getActivity().getApplicationContext(),
            AppDatabase.class, "database-name").build();

    public Fragment_Account() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

}


