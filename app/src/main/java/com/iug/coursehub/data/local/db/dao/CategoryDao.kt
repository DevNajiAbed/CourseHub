package com.iug.coursehub.data.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.iug.coursehub.data.local.db.entity.Category

@Dao
interface CategoryDao {

    @Upsert
    suspend fun upsertCategory(
        category: Category
    )

    @Query("SELECT * FROM category")
    suspend fun getAllCategories(): List<Category>
}