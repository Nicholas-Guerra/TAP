package com.software_engineering.tap.AccountPage;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.ColumnInfo;
import android.support.annotation.NonNull;

import java.util.UUID;

/**
 * Created by grant_000 on 4/4/2018.
 */

@Entity(tableName = "users")
public class User
{
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "userid")
    String UID;

    @ColumnInfo(name = "username")
    String userName;

    @ColumnInfo (name = "first_name")
    String firstName;

    @ColumnInfo(name = "last_name")
    String lastName;

    @ColumnInfo(name = "user_email")
    String email;

    @ColumnInfo(name = "last_update")
    Long date;

    public User (String userName, String firstName, String lastName, String email) {
        this.UID = UUID.randomUUID().toString();
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.date = System.currentTimeMillis();
    }


}
