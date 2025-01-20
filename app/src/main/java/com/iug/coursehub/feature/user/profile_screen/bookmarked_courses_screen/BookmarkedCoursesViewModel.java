package com.iug.coursehub.feature.user.profile_screen.bookmarked_courses_screen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iug.coursehub.data.local.db.CoursesRepository;
import com.iug.coursehub.data.local.db.entity.Course;
import com.iug.coursehub.data.local.prefs.AppPrefs;

import java.util.List;

public class BookmarkedCoursesViewModel extends ViewModel {

    private final MutableLiveData<List<Course>> _bookmarkedCourses = new MutableLiveData<>();
    public LiveData<List<Course>> getBookmarkedCoursesLiveData() {
        return _bookmarkedCourses;
    }

    public void getBookmarkedCourses() {
        String userId = AppPrefs.getCurrentUserId();
        CoroutineScope scope = viewModelScope;
        List<Integer> courseIds = CoursesRepository.getAllBookmarkedCourseIds(userId);
        List<Course> courses = CoursesRepository.getAllCoursesOfIds(courseIds);
        _bookmarkedCourses.postValue(courses);
    }
}
