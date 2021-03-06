package com.software_engineering.tap.TransactionPage;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import com.google.firebase.iid.FirebaseInstanceId;
import com.software_engineering.tap.AccountPage.AppDatabase;
import com.software_engineering.tap.Main_Notifications_Settings.Listener;
import com.software_engineering.tap.Main_Notifications_Settings.MainActivity;
import com.software_engineering.tap.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DialogFragment_Send_Request extends DialogFragment implements View.OnClickListener{

    private View rootView;
    private ListView listView;
    private EditText searchInput;
    private Button search;
    private ImageView close;
    private double amount;
    private ArrayList<String> searchList = new ArrayList<>();
    private ArrayList<String> tokenList = new ArrayList<>();
    private ArrayAdapter<String> listAdapter;
    private Listener mListener;


    public DialogFragment_Send_Request() {
        // Required empty public constructor
    }

    public static DialogFragment_Send_Request newInstance(double amount) {
        Bundle bundle = new Bundle();
        bundle.putDouble("Amount", amount);

        DialogFragment_Send_Request fragment = new DialogFragment_Send_Request();
        fragment.setArguments(bundle);

        return fragment;
    }
    @Override
    public void onStart() {
        super.onStart();
        Dialog d = getDialog();
        if (d!=null){
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            d.getWindow().setLayout(width, height);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dialog_fragment_send_request, container, false);

        mListener = (MainActivity)getContext();
        amount = getArguments().getDouble("Amount");

        close = rootView.findViewById(R.id.close_button);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        searchInput = rootView.findViewById(R.id.searchInput);
        search = rootView.findViewById(R.id.search);
        search.setOnClickListener(this);

        listView = rootView.findViewById(R.id.listView);
        listAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, searchList);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {


                try {
                    JSONObject object = new JSONObject();
                    object.put("Request", "sendNotification")
                            .put("to", tokenList.get(position))
                            .put("toName", searchList.get(position))
                            .put("fromName", MainActivity.getUser().userName)
                            .put("amount", amount);

                    new sendToServer(getContext(), true, "Sending request", object){
                        @Override
                        public void onPostExecute(JSONObject receivedJSON) {
                            super.onPostExecute(receivedJSON);
                            try {
                                String status = receivedJSON.getString("Status");
                                if(status.equals("Complete")){
                                    Toast.makeText(getContext(), "Sent", Toast.LENGTH_SHORT).show();
                                    mListener.refreshPage();
                                    dismiss();
                                } else{
                                    Toast.makeText(getContext(), receivedJSON.getString("Message"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }.execute();


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });



        return rootView;
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void onClick(View v){
        if(v == search){
            if(!searchInput.getText().toString().isEmpty()){

                try {
                    JSONObject object = new JSONObject();
                    object.put("Request", "userRequest")
                            .put("Search", searchInput.getText().toString().replaceAll("[^a-zA-Z0-9]", ""));
                    new sendToServer(getContext(), true, "Searching for users", object){
                        @Override
                        public void onPostExecute(JSONObject receivedJSON) {
                            super.onPostExecute(receivedJSON);
                            try {
                                JSONArray array = receivedJSON.getJSONArray("Array");

                                searchList.clear();
                                tokenList.clear();

                                for (int i = 0; i < array.length(); i++) {
                                    searchList.add(array.getJSONObject(i).getString("userName"));
                                    tokenList.add(array.getJSONObject(i).getString("token"));
                                }

                                listAdapter.notifyDataSetChanged();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }.execute();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}