package com.iug.coursehub.feature.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardNavHostViewModel extends ViewModel {

    private final MutableLiveData<UiAction> _uiAction = new MutableLiveData<>();
    private final LiveData<UiAction> uiAction = _uiAction;

    public LiveData<UiAction> getUiAction() {
        return uiAction;
    }

    public void navigateToAuthScreen() {
        _uiAction.postValue(UiAction.NAVIGATE_TO_AUTH_NAV_HOST_SCREEN);
    }

    public enum UiAction {
        NAVIGATE_TO_AUTH_NAV_HOST_SCREEN
    }
}
