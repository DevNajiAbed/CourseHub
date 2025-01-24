package com.iug.coursehub.feature.user.home_screen.course_details_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iug.coursehub.data.local.db.CoursesRepository
import com.iug.coursehub.data.local.db.entity.Course
import com.iug.coursehub.data.local.db.entity.Enrolled
import com.iug.coursehub.data.local.prefs.AppPrefs
import kotlinx.coroutines.launch

class CourseDetailsViewModel : ViewModel() {

    private val _uiAction = MutableLiveData<UiAction>()
    val uiAction: LiveData<UiAction> = _uiAction

    fun getCourseDetails(courseId: Int) {
        viewModelScope.launch {
            val course = CoursesRepository.getCourseById(courseId)
            val noOfEnrolledStudents = CoursesRepository.getNoOfStudentsEnrolledByCourseId(courseId)
            val noOfLessons = CoursesRepository.getNoOfLessonsByCourseId(courseId)

            val courseDetails = CourseDetails(
                course = course,
                noOfEnrolledStudents = noOfEnrolledStudents,
                noOfLessons = noOfLessons
            )
            _uiAction.postValue(UiAction.CollectCourseDetails(courseDetails))
        }
    }

    fun joinCourse(courseId: Int) {
        val userId = AppPrefs.getCurrentUserId()
        val enrolled = Enrolled(
            userId = userId,
            courseId = courseId
        )
        viewModelScope.launch {
            CoursesRepository.upsertEnrolled(enrolled)
            _uiAction.postValue(UiAction.ShowToast("Joined Successfully!"))
        }
    }

    sealed interface UiAction {
        data class CollectCourseDetails(val courseDetails: CourseDetails) : UiAction
        data class ShowToast(val message: String) : UiAction
    }
}