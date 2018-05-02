package com.software_engineering.tap.AccountPage;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

/**
 * Created by grant_000 on 4/9/2018.
 */
@Entity(tableName = "transactions")
public class Transaction {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "transactionID")
    public String transactionID;

    @ColumnInfo(name = "toFrom")
    public String toFromName;

    @ColumnInfo(name = "amount")
    public double amount;

    @ColumnInfo(name = "status")
    public String status;

    @ColumnInfo(name = "transactionDate")
    public long date;


    public Transaction (String toFromName, double amount, String status, long date, @NonNull String transactionID) {
        this.transactionID = transactionID;
        this.toFromName = toFromName;
        this.status = status;
        this.date = date;
        this.amount = amount;
        this.status = status;
    }

}
