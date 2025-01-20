package com.iug.coursehub.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Upsert;

import com.iug.coursehub.data.local.db.entity.Enrolled;

import java.util.List;

@Dao
public interface EnrolledDao {

    @Query("SELECT SUM(id) FROM enrolled WHERE courseId = :courseId")
    int getNoOfStudentsEnrolledByCourseId(int courseId);

    @Upsert
    void upsertEnrolled(Enrolled enrolled);

    @Query("SELECT courseId FROM enrolled WHERE userId = :userId")
    List<Integer> getEnrolledCourseIds(int userId);
}
