package com.iug.coursehub.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "enrolled"
)
data class Enrolled(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val userId: Int,
    val courseId: Int
)
