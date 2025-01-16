package com.iug.coursehub.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iug.coursehub.data.local.db.dao.BookmarkedDao
import com.iug.coursehub.data.local.db.dao.CourseDao
import com.iug.coursehub.data.local.db.dao.EnrolledDao
import com.iug.coursehub.data.local.db.dao.LessonDao
import com.iug.coursehub.data.local.db.dao.UserDao
import com.iug.coursehub.data.local.db.entity.Bookmarked
import com.iug.coursehub.data.local.db.entity.Course
import com.iug.coursehub.data.local.db.entity.Enrolled
import com.iug.coursehub.data.local.db.entity.Lesson
import com.iug.coursehub.data.local.db.entity.User

@Database(
    entities = [
        User::class,
        Course::class,
        Lesson::class,
        Enrolled::class,
        Bookmarked::class
    ],
    version = 1
)
abstract class CoursesDB : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val courseDao: CourseDao
    abstract val lessonDao: LessonDao
    abstract val enrolledDao: EnrolledDao
    abstract val bookmarkedDao: BookmarkedDao

    companion object {
        const val DB_NAME = "courses_db"
    }
}