package com.software_engineering.tap.AccountPage;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by grant_000 on 4/18/2018.
 */

public class ViewModel_ViewMore_Transaction extends AndroidViewModel {

    private final LiveData<List<Transaction>> transactionList;

    public ViewModel_ViewMore_Transaction(@NonNull Application application)
    {
        super(application);

        transactionList = AppDatabase.getInstance(getApplication()).transactionDao().getAll();
    }

    public LiveData<List<Transaction>> getTransactionNotifications() {

        return transactionList;
    }
}