package com.software_engineering.tap.AccountPage;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.ColumnInfo;
import android.support.annotation.NonNull;



/**
 * Created by grant_000 on 4/4/2018.
 */

@Entity(tableName = "users")
public class User {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "username")
    public String userName;

    @ColumnInfo (name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "user_email")
    public String email;

    @ColumnInfo(name = "last_update")
    public Long date;

    @ColumnInfo(name = "balance")
    public double balance;

    @ColumnInfo(name = "password_Hashed")
    public String passwordHash;

    @ColumnInfo(name = "phone_number")
    public String phoneNum;

    @ColumnInfo(name = "useFingerprint")
    public boolean useFingerprint;

    @ColumnInfo(name = "pin")
    public int pin;


    public User ( String userName, String firstName, String lastName, String email, String passwordHash, double balance, String phoneNum, boolean useFingerprint, int pin) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.date = System.currentTimeMillis();
        this.balance = balance;
        this.passwordHash = passwordHash;
        this.phoneNum = phoneNum;
        this.useFingerprint = useFingerprint;
        this.pin = pin;
    }


}
