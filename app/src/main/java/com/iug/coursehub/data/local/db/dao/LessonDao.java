package com.iug.coursehub.data.local.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Upsert;

import com.iug.coursehub.data.local.db.entity.Lesson;

import java.util.List;

@Dao
public interface LessonDao {

    @Upsert
    void upsertLesson(Lesson lesson);

    @Query("SELECT * FROM lesson WHERE id = :id")
    Lesson getLessonById(int id);

    @Query("SELECT * FROM lesson WHERE courseId = :courseId")
    LiveData<List<Lesson>> getAllLessonsOfCourse(int courseId);

    @Delete
    void deleteLesson(Lesson lesson);

    @Query("SELECT SUM(id) FROM lesson WHERE courseId = :courseId")
    int getNoOfLessonsByCourseId(int courseId);
}