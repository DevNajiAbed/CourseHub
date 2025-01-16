package com.iug.coursehub.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "course"
)
data class Course(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val imageByteArray: ByteArray,
    val price: Float,
    val noOfHours: Int,
    val lecturerName: String,
    val category: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Course

        if (id != other.id) return false
        if (name != other.name) return false
        if (!imageByteArray.contentEquals(other.imageByteArray)) return false
        if (price != other.price) return false
        if (noOfHours != other.noOfHours) return false
        if (lecturerName != other.lecturerName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + imageByteArray.contentHashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + noOfHours
        result = 31 * result + lecturerName.hashCode()
        return result
    }
}