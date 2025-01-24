package com.iug.coursehub.feature.dashboard.add_edit_lesson_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iug.coursehub.data.local.db.CoursesRepository
import com.iug.coursehub.data.local.db.entity.Lesson
import kotlinx.coroutines.launch

class AddEditLessonViewModel : ViewModel() {

    private val _uiAction = MutableLiveData<UiAction>()
    val uiAction: LiveData<UiAction> = _uiAction

    fun saveLesson(lesson: Lesson) {
        if(validateFields(lesson)) {
            viewModelScope.launch {
                CoursesRepository.upsertLesson(lesson)
                _uiAction.postValue(UiAction.NavigateUp)
            }
        }
    }

    private fun validateFields(lesson: Lesson): Boolean {
        lesson.apply {
            if(title.trim().isBlank()) {
                _uiAction.postValue(UiAction.ShowToast("You should insert a title for the lesson"))
                return false
            }
            if(description.trim().isBlank()) {
                _uiAction.postValue(UiAction.ShowToast("You should insert a description for the lesson"))
                return false
            }
            if(ytLink.trim().isBlank()) {
                _uiAction.postValue(UiAction.ShowToast("You should insert a YouTube link for the lesson"))
                return false
            }
        }
        return true
    }

    fun deleteLesson(lesson: Lesson) {
        viewModelScope.launch {
            CoursesRepository.deleteLesson(lesson)
            _uiAction.postValue(UiAction.NavigateUp)
        }
    }

    fun hideDeleteBtn() {
        _uiAction.postValue(UiAction.HideDeleteBtn)
    }

    sealed interface UiAction {
        data class ShowToast(val message: String) : UiAction
        data object HideDeleteBtn : UiAction
        data object NavigateUp : UiAction
    }
}