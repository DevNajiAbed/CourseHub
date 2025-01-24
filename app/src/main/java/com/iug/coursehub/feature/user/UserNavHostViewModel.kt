package com.iug.coursehub.feature.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserNavHostViewModel : ViewModel() {

    private val _uiAction = MutableLiveData<UiAction>()
    val uiAction: LiveData<UiAction> = _uiAction

    fun navigateFromUserToAuth() {
        _uiAction.postValue(UiAction.NavigateFromUserToAuth)
    }

    sealed interface UiAction {
        data object NavigateFromUserToAuth : UiAction
    }
}