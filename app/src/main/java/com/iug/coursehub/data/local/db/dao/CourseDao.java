package com.iug.coursehub.data.local.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Upsert;

import com.iug.coursehub.data.local.db.entity.Course;

import java.util.List;

@Dao
public interface CourseDao {

    @Upsert
    void upsertCourse(Course course);

    @Query("SELECT * FROM course ORDER BY id DESC")
    LiveData<List<Course>> getAllCourses();

    @Query("SELECT * FROM course WHERE id = :id")
    Course getCourseById(int id);

    @Delete
    void deleteCourse(Course course);

    @Query("SELECT * FROM course WHERE category = :category ORDER BY id DESC")
    LiveData<List<Course>> getCoursesByCategory(String category);

    @Query("SELECT * FROM course WHERE id IN (:ids)")
    List<Course> getAllCoursesOfIds(List<Integer> ids);
}
