package com.software_engineering.tap.Login;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.hardware.camera2.TotalCaptureResult;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.app.Dialog;

import org.json.JSONException;
import org.json.JSONObject;

import com.software_engineering.tap.AccountPage.User;
import com.software_engineering.tap.TransactionPage.sendToServer;

import com.software_engineering.tap.AccountPage.AppDatabase;
import com.software_engineering.tap.Main_Notifications_Settings.MainActivity;
import com.software_engineering.tap.R;

public class LoginActivity extends AppCompatActivity{

    private static final int REQUEST_CODE = 100;

    //private static final String TAG = "LoginActivity";

    private Button btnLogin;
    private TextView btnNewUser;
    private EditText userName, userPassword;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName =  findViewById(R.id.user_name);
        userPassword =  findViewById(R.id.user_password);
        btnLogin =  findViewById(R.id.login_button);
        btnNewUser = findViewById(R.id.user_button);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login(userName.getText().toString(), userPassword.getText().toString());

            }
        });



        btnNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //FragmentManager fm = LoginActivity.this.getFragmentManager();
                Fragment_NewUser_Request newUser = new Fragment_NewUser_Request();
                newUser.show(getSupportFragmentManager(), "Fragment_NewUser_Request");

                //startActivity(new Intent(LoginActivity.this, MainActivity.class));

                //newUser.show(getFragmentManager(), Fragment_NewUser_Request.class);

            }
        });

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED ) {
            if (!(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)) ||
                    !(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.USE_FINGERPRINT))){
                ActivityCompat.requestPermissions(LoginActivity.this, new String[]{ Manifest.permission.INTERNET, Manifest.permission.USE_FINGERPRINT}, REQUEST_CODE);
            }
        }

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                user = AppDatabase.getInstance(getBaseContext()).userDao().getUser();
                if(user !=null){
                    userName.setText(user.userName);
                    userName.setEnabled(false);
                }
            }
        });



    }

    @SuppressLint("StaticFieldLeak")
    private void login(final String userName, final String userPassword){

        JSONObject object = new JSONObject();

        try {
            object.put("Request", "Login");
            object.put("userName", userName);
            object.put("hashedPassword",String.valueOf(userPassword.hashCode()) );

            new sendToServer(this,true, "Verifying", object){

                @Override
                public void onPostExecute(JSONObject receivedJSON){
                    super.onPostExecute(receivedJSON);

                    try {
                        String status = receivedJSON.getString("Status");
                        if(status.equals("Complete")){
                            if(user == null){

                                final AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);

                                final EditText edittext = new EditText(LoginActivity.this);
                                edittext.setInputType(InputType.TYPE_CLASS_NUMBER);
                                alert.setMessage("Enter Your Message");
                                alert.setTitle("Enter Your Title");
                                alert.setPositiveButton("Submit", null);

                                alert.setView(edittext);

                                alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        if(edittext.getText().toString().length() <= 3){
                                            Toast.makeText(LoginActivity.this, "Too short: Try again", Toast.LENGTH_SHORT).show();
                                        } else {

                                            user.userName =;
                                            user.firstName =;
                                            user.lastName =;
                                            user.balance =;
                                            user.email =;
                                            user.passwordHash =;
                                            user.date = System.currentTimeMillis();
                                            user.pin = Integer.valueOf(edittext.getText().toString());

                                            dialog.dismiss();

                                        }
                                    }
                                });

                                alert.show();

                            }

                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        } else{
                            Toast.makeText(getBaseContext(), receivedJSON.getString("Message"), Toast.LENGTH_LONG).show();
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        boolean check = true;
        switch (requestCode) {
            case REQUEST_CODE:
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {

                    } else {
                        Toast.makeText(LoginActivity.this, "The app was not allowed to read all the information necessary. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                        check = false;
                    }
                }
                if(check){

                }
                break;
        }
    }

}
