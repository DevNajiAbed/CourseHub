package com.iug.coursehub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _uiAction = MutableLiveData<UiAction>()
    val uiAction: LiveData<UiAction> = _uiAction

    fun navigateAdminToAuthNavHost() {
        _uiAction.postValue(UiAction.NavigateAdminToAuthNavHost)
    }

    fun navigateUserToAuthNavHost() {
        _uiAction.postValue(UiAction.NavigateUserToAuthNavHost)
    }

    fun navigateToDashboardNavHost() {
        _uiAction.postValue(UiAction.NavigateToDashboardNavHost)
    }

    fun navigateToUserNavHost() {
        _uiAction.postValue(UiAction.NavigateToUserNavHost)
    }

    sealed interface UiAction {
        data object NavigateToDashboardNavHost : UiAction
        data object NavigateToUserNavHost : UiAction
        data object NavigateUserToAuthNavHost : UiAction
        data object NavigateAdminToAuthNavHost : UiAction
    }
}