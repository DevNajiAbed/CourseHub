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

    public Lesson( int id, String title, String description, String ytLink, int courseId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ytLink = ytLink;
        this.courseId = courseId;
    }

    public Lesson(String title, String description, String ytLink, int courseId) {
        this.title = title;
        this.description = description;
        this.ytLink = ytLink;
        this.courseId = courseId;
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
