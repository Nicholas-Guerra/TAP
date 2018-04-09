package com.software_engineering.tap.AccountPage;


import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.software_engineering.tap.Main_Notifications_Settings.MainActivity;
import com.software_engineering.tap.R;

import java.util.Date;

public class Fragment_Account extends Fragment {



    public Fragment_Account() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        //TextView nameText = rootView.findViewById(R.id.name);

        //User user = MainActivity.getDb().userDao().getUser();

        //nameText.setText(user.firstName);

        return rootView;
    }

}


