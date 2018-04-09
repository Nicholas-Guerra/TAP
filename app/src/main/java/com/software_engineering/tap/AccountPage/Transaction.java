package com.software_engineering.tap.AccountPage;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

/**
 * Created by grant_000 on 4/9/2018.
 */
@Entity(tableName = "Transactions")
public class Transaction {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "transactionID")
    String TID;

    @ColumnInfo(name = "to/from")
    String toFromName;

    @ColumnInfo(name = "amount")
    double amount;

    @ColumnInfo(name = "status")
    String status;

    @ColumnInfo(name = "transactionDate")
    long date;

    public Transaction (String toFromName, double amount, String status, long date) {
        this.TID = UUID.randomUUID().toString();
        this.toFromName = toFromName;
        this.status = status;
        this.date = System.currentTimeMillis();
        this.amount = amount;
        this.status = status;
    }
}
