package com.iug.coursehub.feature.authentication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AuthViewModel extends ViewModel {

    private final MutableLiveData<UiAction> _uiAction = new MutableLiveData<>();
    private final LiveData<UiAction> uiAction = _uiAction;

    public LiveData<UiAction> getUiAction() {
        return uiAction;
    }

    public void navigateToUserScreen() {
        _uiAction.postValue(UiAction.NAVIGATE_TO_USER_SCREEN);
    }

    public void navigateToAdminScreen() {
        _uiAction.postValue(UiAction.NAVIGATE_TO_ADMIN_SCREEN);
    }

    public enum UiAction {
        NAVIGATE_TO_USER_SCREEN,
        NAVIGATE_TO_ADMIN_SCREEN
    }
}
