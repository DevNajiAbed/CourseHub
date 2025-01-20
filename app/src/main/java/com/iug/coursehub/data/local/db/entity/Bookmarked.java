package com.iug.coursehub.data.local.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(
    tableName = "bookmarked"
)
public class Bookmarked {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int userId;
    private int courseId;

    public Bookmarked(int courseId, int id, int userId) {
        this.courseId = courseId;
        this.id = id;
        this.userId = userId;
    }

    public Bookmarked(int courseId, int userId) {
        this.courseId = courseId;
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
