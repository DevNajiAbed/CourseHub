package com.iug.coursehub.feature.authentication.login_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iug.coursehub.data.local.db.CoursesRepository
import com.iug.coursehub.data.local.prefs.AppPrefs
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _uiAction = MutableLiveData<UiAction>()
    val uiAction: LiveData<UiAction> = _uiAction

    fun saveRememberMe(rememberMe: Boolean) {
        AppPrefs.saveRememberMe(rememberMe)
    }

    fun login(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            _uiAction.postValue(UiAction.Loading)
            if(validateFields(email, password)) {
                val adminEmail = AppPrefs.getAdminEmail()
                val adminPassword = AppPrefs.getAdminPassword()

                if (email == adminEmail && password == adminPassword) {
                    _uiAction.postValue(UiAction.NavigateToAdminScreen)
                    return@launch
                }

                val user = CoursesRepository.getUserByEmailAndPassword(email, password)

                if (email == user.email && password == user.password) {
                    AppPrefs.saveCurrentUserId(user.id)
                    _uiAction.postValue(UiAction.NavigateToUserScreen)
                    return@launch
                }
            }
        }
    }

    private fun validateFields(vararg fields: String): Boolean {
        for(field in fields) {
            if(field.isBlank()) {
                _uiAction.postValue(UiAction.ShowToast("Please fill all fields"))
                return false
            }
        }
        return true
    }

    sealed interface UiAction {
        data class ShowToast(val message: String) : UiAction
        data object NavigateToAdminScreen : UiAction
        data object NavigateToUserScreen : UiAction
        data object Loading : UiAction
    }
}