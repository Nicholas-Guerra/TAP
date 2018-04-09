package com.software_engineering.tap.AccountPage;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;


/**
 * Created by grant_000 on 4/9/2018.
 */
@Dao
public interface TransactionDao {
    @Query("SELECT * FROM TRANSACTIONS LIMIT 4")
    Transaction getRecent();

    @Query("SELECT * FROM TRANSACTIONS")
    Transaction getAll();

    @Query("UPDATE transactions SET toFrom = :toFrom, status = :status, amount = :amount, transactionDate = :transactionDate")
    void updateTransaction(String toFrom, String status, double amount, String transactionDate);

    @Insert
    void insert(Transaction transaction);


}
