package com.iug.coursehub.data.local.db.dao;

import androidx.room.Query;
import androidx.room.Upsert;

import com.iug.coursehub.data.local.db.entity.User;

public interface UserDao {

    @Query("SELECT * FROM user WHERE id = :id")
    User getUserById(int id);

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    User getUserByEmailAndPassword(
        String email,
        String password
    );

    @Upsert
    void upsertUser(User user);
}
