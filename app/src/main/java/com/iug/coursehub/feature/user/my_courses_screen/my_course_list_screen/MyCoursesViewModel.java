package com.iug.coursehub.feature.user.my_courses_screen.my_course_list_screen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iug.coursehub.data.local.db.CoursesRepository;
import com.iug.coursehub.data.local.db.entity.Course;
import com.iug.coursehub.data.local.prefs.AppPrefs;

import java.util.List;

public class MyCoursesViewModel extends ViewModel {

    private MutableLiveData<List<Course>> _courses = new MutableLiveData<>();
    public LiveData<List<Course>> courses = _courses;

    public void getEnrolledCourses() {
        int userId = AppPrefs.getCurrentUserId();
        List<Integer> enrolledCourseIds = CoursesRepository.getEnrolledCourseIds(userId);
        List<Course> enrolledCourses = CoursesRepository.getAllCoursesOfIds(enrolledCourseIds);
        _courses.postValue(enrolledCourses);
    }
}
