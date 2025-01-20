package com.iug.coursehub.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Upsert;

import com.iug.coursehub.data.local.db.entity.Bookmarked;

import java.util.List;

@Dao
public interface BookmarkedDao {

    @Upsert
    void upsertBookmarked(Bookmarked bookmarked);

    @Query("SELECT courseId FROM bookmarked WHERE userId = :userId")
    List<Integer> getAllBookmarkedCourseIds(int userId);
}
