package com.iug.coursehub;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<UiAction> _uiAction = new MutableLiveData<>();
    private final LiveData<UiAction> uiAction = _uiAction;

    public LiveData<UiAction> getUiAction() {
        return uiAction;
    }

    public void navigateToDashboardNavHost() {
        _uiAction.postValue(UiAction.NAVIGATE_TO_DASHBOARD_NAV_HOST);
    }

    public void navigateToUserNavHost() {
        _uiAction.postValue(UiAction.NAVIGATE_TO_USER_NAV_HOST);
    }

    public void navigateUserToAuth() {
        _uiAction.postValue(UiAction.NAVIGATE_USER_TO_AUTH);
    }

    public enum UiAction {
        NAVIGATE_TO_DASHBOARD_NAV_HOST,
        NAVIGATE_TO_USER_NAV_HOST,
        NAVIGATE_USER_TO_AUTH
    }
}
