package com.software_engineering.tap.AccountPage;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.ColumnInfo;

import java.util.Date;
import java.util.UUID;

/**
 * Created by grant_000 on 4/4/2018.
 */
@Entity(tableName = "users")
public class User
{
    @PrimaryKey
    @ColumnInfo(name = "userid")
    private String UID;

    @ColumnInfo(name = "username")
    private String mUserName;

    @ColumnInfo (name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    @ColumnInfo(name = "user_email")
    private String email;

    @ColumnInfo(name = "last_update")
    private Date mDate;

    @Ignore
    public User(String userName) {
        UID = UUID.randomUUID().toString();
        mUserName = userName;
        mDate = new Date(System.currentTimeMillis());

    }

    public User (String UID, String userName, String firstName, String email, Date date) {
        this.UID = UID;
        this.mUserName = userName;
        this.firstName = firstName;
        this.email = email;
        this.mDate = date;
    }


}
