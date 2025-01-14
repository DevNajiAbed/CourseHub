package com.iug.coursehub.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iug.coursehub.data.local.db.dao.CategoryDao
import com.iug.coursehub.data.local.db.dao.UserDao
import com.iug.coursehub.data.local.db.entity.Category
import com.iug.coursehub.data.local.db.entity.User

@Database(
    entities = [
        User::class,
        Category::class
    ],
    version = 1
)
abstract class CoursesDB : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val categoryDao: CategoryDao

    companion object {
        const val DB_NAME = "courses_db"
    }
}