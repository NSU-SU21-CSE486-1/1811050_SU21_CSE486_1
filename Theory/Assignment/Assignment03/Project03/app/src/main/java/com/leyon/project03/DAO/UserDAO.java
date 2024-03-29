package com.leyon.project03.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.leyon.project03.Entity.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM users ORDER BY id ASC")
    LiveData<List<User>> getAllUsers();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(User user);

    @Update
    void update(User user);

    @Delete
    void deleteUser(User user);

    @Query("DELETE from users")
    void deleteAll();
}
