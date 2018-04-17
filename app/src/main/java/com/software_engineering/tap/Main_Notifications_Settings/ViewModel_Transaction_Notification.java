package com.software_engineering.tap.Main_Notifications_Settings;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.software_engineering.tap.AccountPage.AppDatabase;

import java.util.List;

public class ViewModel_Transaction_Notification extends AndroidViewModel {

    private final LiveData<List<Transaction_Notification>> transactionNotifications;

    public ViewModel_Transaction_Notification(@NonNull Application application){
        super(application);

        transactionNotifications = AppDatabase.getInstance(getApplication())
                                    .transaction_notificationDao().getAll();
    }

    public LiveData<List<Transaction_Notification>> getTransactionNotifications() {
        return transactionNotifications;
    }
}
