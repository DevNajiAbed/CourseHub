package com.iug.coursehub.feature.user.profile_screen.profile_home_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iug.coursehub.data.local.db.CoursesRepository
import com.iug.coursehub.data.local.db.entity.User
import com.iug.coursehub.data.local.prefs.AppPrefs
import kotlinx.coroutines.launch

class ProfileHomeViewModel : ViewModel() {

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

    fun signOut() {
        AppPrefs.saveRememberMe(false)
        _uiAction.postValue(UiAction.NavigateToAuth)
    }

    sealed interface UiAction {
        data object NavigateToAuth : UiAction
    }
}