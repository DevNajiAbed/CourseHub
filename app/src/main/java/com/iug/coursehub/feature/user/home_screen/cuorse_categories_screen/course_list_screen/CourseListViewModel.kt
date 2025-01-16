package com.iug.coursehub.feature.user.home_screen.cuorse_categories_screen.course_list_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iug.coursehub.data.local.db.CoursesRepository
import com.iug.coursehub.data.local.db.entity.Bookmarked
import com.iug.coursehub.data.local.db.entity.Course
import com.iug.coursehub.data.local.prefs.AppPrefs
import kotlinx.coroutines.launch

class CourseListViewModel : ViewModel() {

    private val _uiAction = MutableLiveData<UiAction>()
    val uiAction: LiveData<UiAction> = _uiAction

    fun getCourses(category: String): LiveData<List<Course>> {
        return CoursesRepository.getCoursesByCategory(category)
    }

    fun bookmarkCourse(courseId: Int) {
        val userId = AppPrefs.getCurrentUserId()
        val bookmarked = Bookmarked(
            userId = userId,
            courseId = courseId
        )
        viewModelScope.launch {
            CoursesRepository.upsertBookmarked(bookmarked)
            _uiAction.postValue(UiAction.ShowToast("Bookmarked Successfully!"))
        }
    }

    sealed interface UiAction {
        data class ShowToast(val message: String) : UiAction
    }
}