package com.iug.coursehub.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iug.coursehub.data.local.db.dao.CourseDao
import com.iug.coursehub.data.local.db.dao.LessonDao
import com.iug.coursehub.data.local.db.dao.UserDao
import com.iug.coursehub.data.local.db.entity.Course
import com.iug.coursehub.data.local.db.entity.Lesson
import com.iug.coursehub.data.local.db.entity.User

@Database(
    entities = [
        User::class,
        Course::class,
        Lesson::class
    ],
    version = 1
)
abstract class CoursesDB : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val courseDao: CourseDao
    abstract val lessonDao: LessonDao

    companion object {
        const val DB_NAME = "courses_db"
    }
}