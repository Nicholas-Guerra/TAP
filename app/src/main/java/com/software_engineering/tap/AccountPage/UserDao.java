package com.software_engineering.tap.AccountPage;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

/**
 * Created by grant_000 on 4/4/2018.
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM USERS LIMIT 1" )
    User getUser();

    @Query("UPDATE users SET userName = :username, first_name = :first_name, last_name = :last_name, user_email = :user_email, last_update = :last_update")
    void updateUser(String username, String first_name, String last_name, String user_email, Long last_update);

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

}
