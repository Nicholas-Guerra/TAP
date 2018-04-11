package com.software_engineering.tap.AccountPage;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;


/**
 * Created by grant_000 on 4/9/2018.
 */
@Dao
public interface TransactionDao {
    @Query("SELECT * FROM TRANSACTIONS LIMIT 4")
    Transaction getRecent();

    @Query("SELECT * FROM TRANSACTIONS")
    Transaction getAll();

    @Update
    void updateTransaction(Transaction transaction);

    @Insert
    void insert(Transaction transaction);






}
