package com.iug.coursehub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _uiAction = MutableLiveData<UiAction>()
    val uiAction: LiveData<UiAction> = _uiAction

    fun navigateToDashboardNavHost() {
        _uiAction.postValue(UiAction.NavigateToDashboardNavHost)
    }

    fun navigateToUserNavHost() {
        _uiAction.postValue(UiAction.NavigateToUserNavHost)
    }

    fun navigateUserToAuth() {
        _uiAction.postValue(UiAction.NavigateUserToAuth)
    }

    sealed interface UiAction {
        data object NavigateToDashboardNavHost : UiAction
        data object NavigateToUserNavHost : UiAction
        data object NavigateUserToAuth : UiAction
    }
}