package com.iug.coursehub.feature.user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserNavHostViewModel extends ViewModel {

    private final MutableLiveData<UiAction> _uiAction = new MutableLiveData<>();

    public LiveData<UiAction> getUiAction() {
        return _uiAction;
    }

    public void navigateFromUserToAuth() {
        _uiAction.postValue(new UiAction.NavigateFromUserToAuth());
    }

    public static abstract class UiAction {
        public static class NavigateFromUserToAuth extends UiAction {
        }
    }
}
