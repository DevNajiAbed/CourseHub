package com.iug.coursehub.feature.user.my_courses_screen.my_course_list_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iug.coursehub.data.local.db.CoursesRepository
import com.iug.coursehub.data.local.db.entity.Course
import com.iug.coursehub.data.local.prefs.AppPrefs
import kotlinx.coroutines.launch

class MyCoursesViewModel : ViewModel() {

    private val _courses = MutableLiveData<List<Course>>()
    val courses: LiveData<List<Course>> = _courses

    fun getEnrolledCourses() {
        viewModelScope.launch {
            val userId = AppPrefs.getCurrentUserId()
            val enrolledCourseIds = CoursesRepository.getEnrolledCourseIds(userId)
            val enrolledCourses = CoursesRepository.getAllCoursesOfIds(enrolledCourseIds)
            _courses.postValue(enrolledCourses)
        }
    }
}