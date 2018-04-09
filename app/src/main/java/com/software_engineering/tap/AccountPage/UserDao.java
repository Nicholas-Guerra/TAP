package com.software_engineering.tap.AccountPage;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by grant_000 on 4/4/2018.
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM  user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND "
            + "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Query("SELECT * FROM user WHERE email IN :email LIMIT 1" )
    User findByEmail(String email);



    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

}
