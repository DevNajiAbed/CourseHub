package com.iug.coursehub.feature.user.profile_screen.bookmarked_courses_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iug.coursehub.data.local.db.CoursesRepository
import com.iug.coursehub.data.local.db.entity.Course
import com.iug.coursehub.data.local.prefs.AppPrefs
import kotlinx.coroutines.launch

class BookmarkedCoursesViewModel : ViewModel() {

    private val _bookmarkedCourses = MutableLiveData<List<Course>>()
    val bookmarkedCourses: LiveData<List<Course>> = _bookmarkedCourses

    fun getBookmarkedCourses() {
        val userId = AppPrefs.getCurrentUserId()
        viewModelScope.launch {
            val courseIds = CoursesRepository.getAllBookmarkedCourseIds(userId)
            val courses = CoursesRepository.getAllCoursesOfIds(courseIds)
            _bookmarkedCourses.postValue(courses)
        }
    }
}