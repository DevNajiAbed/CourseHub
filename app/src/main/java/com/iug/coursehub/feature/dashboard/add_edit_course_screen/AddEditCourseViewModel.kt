package com.iug.coursehub.feature.dashboard.add_edit_course_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iug.coursehub.data.local.db.CoursesRepository
import com.iug.coursehub.data.local.db.entity.Course
import kotlinx.coroutines.launch

class AddEditCourseViewModel : ViewModel() {

    private val _uiAction = MutableLiveData<UiAction>()
    val uiAction: LiveData<UiAction> = _uiAction

    fun saveCourse(course: Course) {
        if(validateFields(course)) {
            viewModelScope.launch {
                CoursesRepository.upsertCourse(course)
                _uiAction.postValue(UiAction.NavigateUp)
            }
        }
    }

    private fun validateFields(course: Course): Boolean {
        course.apply {
            if(imageByteArray.isEmpty()) {
                _uiAction.postValue(UiAction.ShowToast("You should pick an image"))
                return false
            }
            if(name.trim().isBlank()) {
                _uiAction.postValue(UiAction.ShowToast("You should insert a name for the course"))
                return false
            }
            if(price <= 0) {
                _uiAction.postValue(UiAction.ShowToast("You should insert a valid price"))
                return false
            }
            if(noOfHours <= 0) {
                _uiAction.postValue(UiAction.ShowToast("You should insert a valid number of hours"))
                return false
            }
            if(lecturerName.trim().isBlank()) {
                _uiAction.postValue(UiAction.ShowToast("You should insert a name for the lecturer of the course"))
                return false
            }
        }
        return true
    }

    sealed interface UiAction {
        data class ShowToast(val message: String) : UiAction
        data object NavigateUp : UiAction
    }
}