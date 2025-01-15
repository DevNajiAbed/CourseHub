package com.iug.coursehub.feature.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardNavHostViewModel : ViewModel() {

    private val _uiAction = MutableLiveData<UiAction>()
    val uiAction: LiveData<UiAction> = _uiAction

    fun navigateToAuthScreen() {
        _uiAction.postValue(UiAction.NavigateToAuthNavHostScreen)
    }

    sealed interface UiAction {
        data object NavigateToAuthNavHostScreen : UiAction
    }
}