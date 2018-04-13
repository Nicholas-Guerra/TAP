package com.software_engineering.tap.Login;

import android.Manifest;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.software_engineering.tap.AccountPage.AppDatabase;
import com.software_engineering.tap.Main_Notifications_Settings.MainActivity;
import com.software_engineering.tap.R;

public class LoginActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 100;
    private static AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button = findViewById(R.id.login_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });

        TextView button1 = findViewById(R.id.user_button);
        button1.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                startActivity(new Intent(LoginActivity.this, MainActivity.class));

            }
        });

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").fallbackToDestructiveMigration().build();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED ) {
            if (!(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)) ||
                    !(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.USE_FINGERPRINT))){
                ActivityCompat.requestPermissions(LoginActivity.this, new String[]{ Manifest.permission.INTERNET, Manifest.permission.USE_FINGERPRINT}, REQUEST_CODE);
            }
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

    public static AppDatabase getDb(){
        return db;
    }
}
