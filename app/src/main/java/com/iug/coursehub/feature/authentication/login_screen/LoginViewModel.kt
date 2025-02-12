package com.iug.coursehub.feature.authentication.login_screen

import android.util.Log
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

    private var rememberMe: Boolean = false

    fun saveRememberMe(rememberMe: Boolean) {
        this.rememberMe = rememberMe
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
                    AppPrefs.saveRememberMe(rememberMe)
                    if(rememberMe) {
                        AppPrefs.saveCurrUserAsAdmin()
                    }
                    _uiAction.postValue(UiAction.NavigateToAdminScreen)
                    return@launch
                }

                val user = CoursesRepository.getUserByEmailAndPassword(email, password)

                if(user == null) {
                    _uiAction.postValue(UiAction.ShowToast("Error in email or password"))
                    return@launch
                }

                if (email == user.email && password == user.password) {
                    AppPrefs.saveRememberMe(rememberMe)
                    AppPrefs.saveCurrentUserId(user.id!!)
                    _uiAction.postValue(UiAction.NavigateToUserScreen)
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