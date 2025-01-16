package com.iug.coursehub.feature.user.home_screen.cuorse_categories_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CourseCategoriesViewModel : ViewModel() {

    private val _uiAction = MutableLiveData<UiAction>()
    val uiAction: LiveData<UiAction> = _uiAction

    fun navigateToCourseDetails(courseId: Int) {
        _uiAction.postValue(UiAction.NavigateToCourseDetails(courseId))
    }

    sealed interface UiAction {
        data class NavigateToCourseDetails(val courseId: Int) : UiAction
    }
}