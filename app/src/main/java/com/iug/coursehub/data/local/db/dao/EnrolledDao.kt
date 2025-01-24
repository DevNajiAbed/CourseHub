package com.iug.coursehub.data.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.iug.coursehub.data.local.db.entity.Enrolled

@Dao
interface EnrolledDao {

    @Query("SELECT SUM(id) FROM enrolled WHERE courseId = :courseId")
    suspend fun getNoOfStudentsEnrolledByCourseId(courseId: Int): Int

    @Upsert
    suspend fun upsertEnrolled(enrolled: Enrolled)

    @Query("SELECT courseId FROM enrolled WHERE userId = :userId")
    suspend fun getEnrolledCourseIds(userId: Int): List<Int>
}