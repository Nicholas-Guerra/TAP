package com.software_engineering.tap.Main_Notifications_Settings;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "transaction_notifications", primaryKeys = {"toFrom", "transactionDate"})
public class Transaction_Notification {

    @NonNull
    @ColumnInfo(name = "toFrom")
    public String toName;

    @ColumnInfo(name = "amount")
    public double amount;

    @NonNull
    @ColumnInfo(name = "transactionDate")
    public long date;

    @NonNull
    @ColumnInfo(name = "transactionID")
    public String transactionID;

    public Transaction_Notification (String toName, double amount, long date, String transactionID) {
        this.toName = toName;
        this.date = date;
        this.amount = amount;
        this.transactionID = transactionID;
    }

}