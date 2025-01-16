package com.iug.coursehub.data.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.iug.coursehub.data.local.db.entity.Bookmarked

@Dao
interface BookmarkedDao {

    @Upsert
    suspend fun upsertBookmarked(bookmarked: Bookmarked)

    @Query("SELECT courseId FROM bookmarked WHERE userId = :userId")
    fun getAllBookmarkedCourseIds(userId: Int): List<Int>
}