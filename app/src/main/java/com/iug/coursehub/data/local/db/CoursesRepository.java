package com.iug.coursehub.data.local.db;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.iug.coursehub.data.local.db.entity.Bookmarked;
import com.iug.coursehub.data.local.db.entity.Course;
import com.iug.coursehub.data.local.db.entity.Enrolled;
import com.iug.coursehub.data.local.db.entity.Lesson;
import com.iug.coursehub.data.local.db.entity.User;

import java.util.List;

public class CoursesRepository {

    private static CoursesDB db = null;

    public static void initDb(Context context) {
        if(db == null)
            db = Room.databaseBuilder(
                context,
                CoursesDB.class,
                CoursesDB.DB_NAME
            ).allowMainThreadQueries()
                    .build();
    }

    //region User Operations
    public static User getUserById(int id) {
        return db.getUserDao().getUserById(id);
    }

    public static User getUserByEmailAndPassword(
        String email,
        String password
    ) {
        return db.getUserDao().getUserByEmailAndPassword(email, password);
    }

    public static void upsertUser(User user) {
        db.getUserDao().upsertUser(user);
    }
    //endregion

    //region Course Operations
    public static void upsertCourse(Course course) {
        db.getCourseDao().upsertCourse(course);
    }

    public static LiveData<List<Course>> getAllCourses() {
        return db.getCourseDao().getAllCourses();
    }

    public static Course getCourseById(int id) {
        return db.getCourseDao().getCourseById(id);
    }

    public static void deleteCourse(Course course) {
        db.getCourseDao().deleteCourse(course);
    }

    public static LiveData<List<Course>> getCoursesByCategory(String category) {
        return db.getCourseDao().getCoursesByCategory(category);
    }

    public static List<Course> getAllCoursesOfIds(List<Integer> ids) {
        return db.getCourseDao().getAllCoursesOfIds(ids);
    }
    //endregion

    //region Lesson Operations
    public static void upsertLesson(Lesson lesson) {
        db.getLessonDao().upsertLesson(lesson);
    }

    public static Lesson getLessonById(int id) {
        return db.getLessonDao().getLessonById(id);
    }

    public static LiveData<List<Lesson>> getAllLessonsOfCourse(int courseId) {
        return db.getLessonDao().getAllLessonsOfCourse(courseId);
    }

    public static void deleteLesson(Lesson lesson) {
        db.getLessonDao().deleteLesson(lesson);
    }

    public static int getNoOfLessonsByCourseId(int courseId) {
        return db.getLessonDao().getNoOfLessonsByCourseId(courseId);
    }
    //endregion

    //region Enrolled Operations
    public static int getNoOfStudentsEnrolledByCourseId(int courseId) {
        return db.getEnrolledDao().getNoOfStudentsEnrolledByCourseId(courseId);
    }

    public static void upsertEnrolled(Enrolled enrolled) {
        db.getEnrolledDao().upsertEnrolled(enrolled);
    }

    public static List<Integer> getEnrolledCourseIds(int userId) {
        return db.getEnrolledDao().getEnrolledCourseIds(userId);
    }
    //endregion

    //region Bookmarked Operations
    public static void upsertBookmarked(Bookmarked bookmarked) {
        db.getBookmarkedDao().upsertBookmarked(bookmarked);
    }

    public static List<Integer> getAllBookmarkedCourseIds(int userId) {
        return db.getBookmarkedDao().getAllBookmarkedCourseIds(userId);
    }
    //endregion
}
