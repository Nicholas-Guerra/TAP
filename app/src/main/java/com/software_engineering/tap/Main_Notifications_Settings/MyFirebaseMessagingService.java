package com.software_engineering.tap.Main_Notifications_Settings;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.software_engineering.tap.AccountPage.AppDatabase;
import com.software_engineering.tap.Login.LoginActivity;
import com.software_engineering.tap.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FCM Service";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP |Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent,
                PendingIntent.FLAG_ONE_SHOT);



        String username = remoteMessage.getData().get("User Name");
        double amount = Double.valueOf(remoteMessage.getData().get("Amount"));
        Long date = Long.valueOf(remoteMessage.getData().get("Date"));
        String id = remoteMessage.getData().get("transactionID");

        Log.i("DataTransfer", username + "     " + String.valueOf(amount));

        AppDatabase.getInstance(this).transaction_notificationDao().insert(new Transaction_Notification(username, amount, date, id));







        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            /* Create or update. */
            NotificationChannel channel = new NotificationChannel("Request",
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "Request")
                .setSmallIcon(R.drawable.tap)
                .setContentTitle("Transaction Request")
                .setContentText("From " + username)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);



        notificationManager.notify(NotificationID.getID(), mBuilder.build());




    }
}