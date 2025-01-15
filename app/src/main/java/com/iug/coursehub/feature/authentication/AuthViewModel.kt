package com.iug.coursehub.feature.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    private val _uiAction = MutableLiveData<UiAction>()
    val uiAction: LiveData<UiAction> = _uiAction

    fun navigateToUserScreen() {
        _uiAction.postValue(UiAction.NavigateToUserScreen)
    }

    fun navigateToAdminScreen() {
        _uiAction.postValue(UiAction.NavigateToAdminScreen)
    }

    sealed interface UiAction {
        data object NavigateToUserScreen : UiAction
        data object NavigateToAdminScreen : UiAction
    }
}