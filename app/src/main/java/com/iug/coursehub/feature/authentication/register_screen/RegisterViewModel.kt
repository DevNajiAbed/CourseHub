package com.iug.coursehub.feature.authentication.register_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iug.coursehub.data.local.db.CoursesRepository
import com.iug.coursehub.data.local.db.entity.User
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val _uiAction = MutableLiveData<UiAction>()
    val uiAction: LiveData<UiAction> = _uiAction

    fun signUp(
        username: String,
        email: String,
        password: String
    ) {
        if(validateFields(username, email, password)) {
            val user = User(
                username = username,
                email = email,
                password = password
            )
            viewModelScope.launch {
                CoursesRepository.upsertUser(user)
            }
            _uiAction.postValue(UiAction.NavigateUp)
        }
    }

    private fun validateFields(vararg fields: String): Boolean {
        fields.forEach { field ->
            if(field.isBlank()) {
                _uiAction.postValue(UiAction.ShowToast("All fields are required"))
                return false
            }
        }
        return true
    }

    sealed interface UiAction {
        data class ShowToast(val message: String) : UiAction
        data object NavigateUp : UiAction
    }
}