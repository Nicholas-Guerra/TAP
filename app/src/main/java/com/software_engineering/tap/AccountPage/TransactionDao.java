package com.software_engineering.tap.AccountPage;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import java.util.List;


/**
 * Created by grant_000 on 4/9/2018.
 */
@Dao
public interface TransactionDao {
    @Query("SELECT * FROM TRANSACTIONS ORDER BY DATE DESC LIMIT 3")
    List<Transaction> getRecent();

    @Query("SELECT * FROM TRANSACTIONS")
    LiveData<List<Transaction>> getAll();

    @Update
    void updateTransaction(Transaction transaction);

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(Transaction transaction);

}
