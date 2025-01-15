package com.iug.coursehub.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "lesson"
)
data class Lesson(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val description: String,
    val ytLink: String,
    val courseId: Int
)
