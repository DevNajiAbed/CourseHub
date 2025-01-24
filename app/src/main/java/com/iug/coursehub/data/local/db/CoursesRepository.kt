package com.iug.coursehub.data.local.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.iug.coursehub.data.local.db.entity.Bookmarked
import com.iug.coursehub.data.local.db.entity.Course
import com.iug.coursehub.data.local.db.entity.Enrolled
import com.iug.coursehub.data.local.db.entity.Lesson
import com.iug.coursehub.data.local.db.entity.User

object CoursesRepository {

    private lateinit var db: CoursesDB

    fun initDb(context: Context) {
        db = Room.databaseBuilder(
            context,
            CoursesDB::class.java,
            CoursesDB.DB_NAME
        ).allowMainThreadQueries()
            .build()
    }

    //region User Operations
    suspend fun getUserById(id: Int): User {
        return db.userDao.getUserById(id)
    }

    suspend fun getUserByEmailAndPassword(
        email: String,
        password: String
    ): User? {
        return db.userDao.getUserByEmailAndPassword(email, password)
    }

    suspend fun upsertUser(user: User) {
        db.userDao.upsertUser(user)
    }
    //endregion

    //region Course Operations
    suspend fun upsertCourse(course: Course) {
        db.courseDao.upsertCourse(course)
    }

    fun getAllCourses(): LiveData<List<Course>> {
        return db.courseDao.getAllCourses()
    }

    suspend fun getCourseById(id: Int): Course {
        return db.courseDao.getCourseById(id)
    }

    suspend fun deleteCourse(course: Course) {
        db.courseDao.deleteCourse(course)
    }

    fun getCoursesByCategory(category: String): LiveData<List<Course>> {
        return db.courseDao.getCoursesByCategory(category)
    }

    suspend fun getAllCoursesOfIds(ids: List<Int>): List<Course> {
        return db.courseDao.getAllCoursesOfIds(ids)
    }
    //endregion

    //region Lesson Operations
    suspend fun upsertLesson(lesson: Lesson) {
        db.lessonDao.upsertLesson(lesson)
    }

    suspend fun getLessonById(id: Int): Lesson {
        return db.lessonDao.getLessonById(id)
    }

    fun getAllLessonsOfCourse(courseId: Int): LiveData<List<Lesson>> {
        return db.lessonDao.getAllLessonsOfCourse(courseId)
    }

    suspend fun deleteLesson(lesson: Lesson) {
        db.lessonDao.deleteLesson(lesson)
    }

    suspend fun getNoOfLessonsByCourseId(courseId: Int): Int {
        return db.lessonDao.getNoOfLessonsByCourseId(courseId)
    }
    //endregion

    //region Enrolled Operations
    suspend fun getNoOfStudentsEnrolledByCourseId(courseId: Int): Int {
        return db.enrolledDao.getNoOfStudentsEnrolledByCourseId(courseId)
    }

    suspend fun upsertEnrolled(enrolled: Enrolled) {
        db.enrolledDao.upsertEnrolled(enrolled)
    }

    suspend fun getEnrolledCourseIds(userId: Int): List<Int> {
        return db.enrolledDao.getEnrolledCourseIds(userId)
    }
    //endregion

    //region Bookmarked Operations
    suspend fun upsertBookmarked(bookmarked: Bookmarked) {
        db.bookmarkedDao.upsertBookmarked(bookmarked)
    }

    fun getAllBookmarkedCourseIds(userId: Int): List<Int> {
        return db.bookmarkedDao.getAllBookmarkedCourseIds(userId)
    }
    //endregion
}