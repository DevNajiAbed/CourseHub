package com.iug.coursehub.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.iug.coursehub.data.local.db.dao.BookmarkedDao;
import com.iug.coursehub.data.local.db.dao.CourseDao;
import com.iug.coursehub.data.local.db.dao.EnrolledDao;
import com.iug.coursehub.data.local.db.dao.LessonDao;
import com.iug.coursehub.data.local.db.dao.UserDao;
import com.iug.coursehub.data.local.db.entity.Bookmarked;
import com.iug.coursehub.data.local.db.entity.Course;
import com.iug.coursehub.data.local.db.entity.Enrolled;
import com.iug.coursehub.data.local.db.entity.Lesson;
import com.iug.coursehub.data.local.db.entity.User;

@Database(
    entities = {
        User.class,
        Course.class,
        Lesson.class,
        Enrolled.class,
        Bookmarked.class
    },
    version = 1
)
public abstract class CoursesDB extends RoomDatabase {

    public static String DB_NAME = "courses_db";

    public abstract UserDao getUserDao();
    public abstract CourseDao getCourseDao();
    public abstract LessonDao getLessonDao();
    public abstract EnrolledDao getEnrolledDao();
    public abstract BookmarkedDao getBookmarkedDao();
}
