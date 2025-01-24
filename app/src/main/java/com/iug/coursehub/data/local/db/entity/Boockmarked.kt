package com.iug.coursehub.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "bookmarked"
)
data class Bookmarked(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val userId: Int,
    val courseId: Int
)