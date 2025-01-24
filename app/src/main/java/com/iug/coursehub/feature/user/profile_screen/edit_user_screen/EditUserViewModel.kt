package com.iug.coursehub.feature.user.profile_screen.edit_user_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iug.coursehub.data.local.db.CoursesRepository
import com.iug.coursehub.data.local.db.entity.User
import com.iug.coursehub.data.local.prefs.AppPrefs
import kotlinx.coroutines.launch

class EditUserViewModel : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    private val _uiAction = MutableLiveData<UiAction>()
    val uiAction: LiveData<UiAction> = _uiAction

    fun getUser() {
        val userId = AppPrefs.getCurrentUserId()
        viewModelScope.launch {
            val user = CoursesRepository.getUserById(userId)
            _user.postValue(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            val userId = AppPrefs.getCurrentUserId()
            CoursesRepository.upsertUser(user.copy(id = userId))
            _uiAction.postValue(UiAction.NavigateUp)
        }
    }

    sealed interface UiAction {
        data object NavigateUp : UiAction
    }
}