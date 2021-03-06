package com.software_engineering.tap.Login;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;


import com.google.firebase.iid.FirebaseInstanceId;
import com.software_engineering.tap.AccountPage.AppDatabase;
import com.software_engineering.tap.Main_Notifications_Settings.FirebaseIDService;
import com.software_engineering.tap.Main_Notifications_Settings.MainActivity;
import com.software_engineering.tap.R;
import com.software_engineering.tap.TransactionPage.sendToServer;
import com.software_engineering.tap.AccountPage.User;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;


public class Fragment_NewUser_Request extends DialogFragment {

    View rootView;
    Button submit;
    EditText et1, et2, et3, et4, et5, et6, et7;
    RadioButton cb1, cb2;
    CheckBox fingerprint;
    ImageView close;


    public Fragment_NewUser_Request() {
        //Constructor Needed
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dialog_fragment_newuser_request, container, false);

        submit =  rootView.findViewById(R.id.Submit);
        et1 = rootView.findViewById(R.id.New_UserEdit);
        et2 =  rootView.findViewById(R.id.New_FirstEdit);
        et3 =  rootView.findViewById(R.id.New_LastEdit);
        et4 = rootView.findViewById(R.id.New_PhoneEdit);
        et5 =  rootView.findViewById(R.id.New_EditEmail);
        et6 = rootView.findViewById(R.id.New_PassEdit);
        et7 = rootView.findViewById(R.id.New_PinEdit);
        cb1 = rootView.findViewById(R.id.user_kiosk2);
        cb2 =  rootView.findViewById(R.id.user_cust);
        fingerprint = rootView.findViewById(R.id.Fingerprint);
        close = rootView.findViewById(R.id.close_button);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(et1.getText().toString().isEmpty() ||
                        et2.getText().toString().isEmpty() ||
                        et3.getText().toString().isEmpty() ||
                        et4.getText().toString().isEmpty() ||
                        et5.getText().toString().isEmpty() ||
                        et6.getText().toString().isEmpty() ||
                        et7.getText().toString().isEmpty() ||
                        et7.getText().toString().length() < 4){

                    if(et1.getText().toString().isEmpty()){
                        et1.setError("Required");
                    }
                    if(et2.getText().toString().isEmpty()){
                        et2.setError("Required");
                    }
                    if(et3.getText().toString().isEmpty()){
                        et3.setError("Required");
                    }
                    if(et4.getText().toString().isEmpty()){
                        et4.setError("Required");
                    }
                    if(et5.getText().toString().isEmpty()){
                        et5.setError("Required");
                    }
                    if(et6.getText().toString().isEmpty()){
                        et6.setError("Required");
                    }
                    if(et7.getText().toString().isEmpty()){
                        et7.setError("Required");
                    }
                    if(et7.getText().toString().length() < 4){
                        et7.setError("Longer pin required");
                    }

                } else{
                    newuser(et1.getText().toString(), et2.getText().toString(), et3.getText().toString(), et4.getText().toString(), et5.getText().toString(),
                            et6.getText().toString(), Integer.valueOf(et7.getText().toString()));
                }
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


    @SuppressLint("StaticFieldLeak")
    private void newuser(final String et1, final String et2, final String et3, final String et4, final String et5, final String et6,
                         final int et7) {

        JSONObject object = new JSONObject();

        try {
            object.put("Request", "NewUser");
            object.put("userName", et1);
            object.put("firstName", et2);
            object.put("lastName", et3);
            object.put("phoneNumber", et4);
            object.put("email", et5);
            object.put("hashedPassword", String.valueOf(et6.hashCode()));
            object.put("pin", et7);
            object.put("token", FirebaseInstanceId.getInstance().getToken());

            new sendToServer( getActivity(), true, "Verifying", object) {

                @Override
                public void onPostExecute(final JSONObject receivedJSON) {


                    try {
                        String status = receivedJSON.getString("Status");
                        String message;
                        if (!status.equals("Complete")) {

                            message = receivedJSON.getString("Message");
                            if (message.equalsIgnoreCase("username already in use"))
                                Fragment_NewUser_Request.this.et1.setError("Already taken");
                            else if (message.equalsIgnoreCase("email already in use"))
                                Fragment_NewUser_Request.this.et5.setError("Already taken");
                            else
                                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();

                        }else{
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        AppDatabase.getInstance(getContext()).userDao().deleteALL();
                                        AppDatabase.getInstance(getContext()).transactionDao().deleteALL();
                                        AppDatabase.getInstance(getContext()).transaction_notificationDao().deleteALL();

                                        Long balance = receivedJSON.getLong("balance");
                                        User user = new User(et1, et2, et3, et4, et5 , balance, et6 , fingerprint.isChecked(), et7, FirebaseInstanceId.getInstance().getToken());
                                        AppDatabase.getInstance(getContext()).userDao().insert(user);
                                        LoginActivity.setUser(user, getActivity());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Log.e("JSONError", e.getMessage());
                                    }

                                }
                            });

                            dismiss();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("JSONError", e.getMessage());
                    }
                    super.onPostExecute(receivedJSON);

                }

            }.execute();

        } catch (JSONException e) {

            e.printStackTrace();

        }


    }

}