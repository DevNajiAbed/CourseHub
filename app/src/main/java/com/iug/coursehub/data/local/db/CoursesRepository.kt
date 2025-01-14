package com.iug.coursehub.data.local.db

import android.content.Context
import androidx.room.Room
import com.iug.coursehub.data.local.db.entity.User

object CoursesRepository {

    private lateinit var db: CoursesDB

    fun initDb(context: Context) {
        db = Room.databaseBuilder(
            context,
            CoursesDB::class.java,
            CoursesDB.DB_NAME
        ).build()
    }

    //region User Operations
    suspend fun getUserById(id: Int): User {
        return db.userDao.getUserById(id)
    }

    suspend fun getUserByEmailAndPassword(
        email: String,
        password: String
    ): User {
        return db.userDao.getUserByEmailAndPassword(email, password)
    }

    suspend fun upsertUser(user: User) {
        db.userDao.upsertUser(user)
    }
    //endregion
}