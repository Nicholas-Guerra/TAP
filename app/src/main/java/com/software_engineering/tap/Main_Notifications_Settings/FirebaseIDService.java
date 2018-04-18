package com.software_engineering.tap.Main_Notifications_Settings;

import android.annotation.SuppressLint;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.software_engineering.tap.AccountPage.AppDatabase;
import com.software_engineering.tap.AccountPage.User;
import com.software_engineering.tap.TransactionPage.sendToServer;

import org.json.JSONException;
import org.json.JSONObject;


public class FirebaseIDService extends FirebaseInstanceIdService {
    private static final String TAG = "FirebaseIDService";

    @Override
    public void onTokenRefresh() {
        final String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        new Thread(new Runnable() {
            @Override
            public void run() {
                User user;
                if((user = AppDatabase.getInstance(getBaseContext()).userDao().getUser()) != null){
                    user.token = refreshedToken;
                    AppDatabase.getInstance(getBaseContext()).userDao().update(user);
                    //sendRegistrationToServer(refreshedToken);
                }
            }
        }).start();

    }

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    @SuppressLint("StaticFieldLeak")
    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.

        JSONObject object = new JSONObject();
        try {
            object.put("Request", "Update Token")
                    .put("userName", AppDatabase.getInstance(this).userDao().getUser().userName)
                    .put("token", token);
            new sendToServer(getApplicationContext(), true,"Connecting", object) {
                @Override
                public void onPostExecute(JSONObject receivedJSON) {
                    super.onPostExecute(receivedJSON);


                }
            }.execute();
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
}