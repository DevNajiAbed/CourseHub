package com.iug.coursehub.data.local.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(
    tableName = "lesson"
)
public class Lesson {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private String ytLink;
    private int courseId;

    public Lesson(int courseId, String description, int id, String title, String ytLink) {
        this.courseId = courseId;
        this.description = description;
        this.id = id;
        this.title = title;
        this.ytLink = ytLink;
    }

    public Lesson(int courseId, String description, String title, String ytLink) {
        this.courseId = courseId;
        this.description = description;
        this.title = title;
        this.ytLink = ytLink;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYtLink() {
        return ytLink;
    }

    public void setYtLink(String ytLink) {
        this.ytLink = ytLink;
    }
}
