package com.software_engineering.tap.Main_Notifications_Settings;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface Transaction_NotificationDao {
    @Query("SELECT * FROM TRANSACTION_NOTIFICATIONS")
    LiveData<List<Transaction_Notification>> getAll();

    @Update
    void updateTransaction(Transaction_Notification transaction_notification);

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(Transaction_Notification transaction_notification);

}