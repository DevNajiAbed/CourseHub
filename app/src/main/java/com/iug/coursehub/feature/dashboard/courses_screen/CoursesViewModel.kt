package com.iug.coursehub.feature.dashboard.courses_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iug.coursehub.data.local.db.CoursesRepository
import com.iug.coursehub.data.local.db.entity.Course
import com.iug.coursehub.data.local.prefs.AppPrefs
import kotlinx.coroutines.launch

class CoursesViewModel : ViewModel() {

    private val _uiAction = MutableLiveData<UiAction>()
    val uiAction: LiveData<UiAction> = _uiAction

    fun deleteCourse(course: Course) {
        viewModelScope.launch {
            CoursesRepository.deleteCourse(course)
        }
    }

    fun signOut() {
        AppPrefs.saveRememberMe(false)
        AppPrefs.unSaveCurrUserAsAdmin()
        _uiAction.postValue(UiAction.NavigateToAuthScreen)
    }

    sealed interface UiAction {
        data object NavigateToAuthScreen : UiAction
    }
}