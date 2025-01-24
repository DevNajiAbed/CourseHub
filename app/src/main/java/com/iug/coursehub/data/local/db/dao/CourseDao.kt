package com.iug.coursehub.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.iug.coursehub.data.local.db.entity.Course

@Dao
interface CourseDao {

    @Upsert
    suspend fun upsertCourse(course: Course)

    @Query("SELECT * FROM course ORDER BY id DESC")
    fun getAllCourses(): LiveData<List<Course>>

    @Query("SELECT * FROM course WHERE id = :id")
    suspend fun getCourseById(id: Int): Course

    @Delete
    suspend fun deleteCourse(course: Course)

    @Query("SELECT * FROM course WHERE category = :category ORDER BY id DESC")
    fun getCoursesByCategory(category: String): LiveData<List<Course>>

    @Query("SELECT * FROM course WHERE id IN (:ids)")
    suspend fun getAllCoursesOfIds(ids: List<Int>): List<Course>
}