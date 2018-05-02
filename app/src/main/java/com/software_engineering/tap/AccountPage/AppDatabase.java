package com.software_engineering.tap.AccountPage;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.software_engineering.tap.Main_Notifications_Settings.Transaction_Notification;
import com.software_engineering.tap.Main_Notifications_Settings.Transaction_NotificationDao;

/**
 * Created by grant_000 on 4/4/2018.
 */

@Database(entities = {User.class, Transaction.class, Transaction_Notification.class}, version = 10, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract TransactionDao transactionDao();
    public abstract Transaction_NotificationDao transaction_notificationDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {

            INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase.class,
                    "AppDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return INSTANCE;
    }

}