package com.iug.coursehub.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.iug.coursehub.data.local.db.entity.Course
import com.iug.coursehub.data.local.db.entity.Lesson

@Dao
interface LessonDao {

    @Upsert
    suspend fun upsertLesson(lesson: Lesson)

    @Query("SELECT * FROM lesson WHERE id = :id")
    suspend fun getLessonById(id: Int): Lesson

    @Query("SELECT * FROM lesson WHERE courseId = :courseId")
    fun getAllLessonsOfCourse(courseId: Int): LiveData<List<Lesson>>

    @Delete
    suspend fun deleteLesson(lesson: Lesson)

    @Query("SELECT SUM(id) FROM lesson WHERE courseId = :courseId")
    fun getNoOfLessonsByCourseId(courseId: Int): Int
}