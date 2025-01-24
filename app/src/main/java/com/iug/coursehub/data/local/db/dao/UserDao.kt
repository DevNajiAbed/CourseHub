package com.iug.coursehub.data.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.iug.coursehub.data.local.db.entity.User

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertUser(user: User)

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUserById(id: Int): User

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    suspend fun getUserByEmailAndPassword(
        email: String,
        password: String
    ): User?
}