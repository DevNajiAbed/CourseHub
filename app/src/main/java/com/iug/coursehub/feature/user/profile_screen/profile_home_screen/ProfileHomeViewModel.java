package com.iug.coursehub.feature.user.profile_screen.profile_home_screen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iug.coursehub.data.local.db.CoursesRepository;
import com.iug.coursehub.data.local.db.entity.User;
import com.iug.coursehub.data.local.prefs.AppPrefs;

public class ProfileHomeViewModel extends ViewModel {

    private final MutableLiveData<User> _user = new MutableLiveData<>();
    public LiveData<User> getUserLiveData() {
        return _user;
    }

    private final MutableLiveData<UiAction> _uiAction = new MutableLiveData<>();
    public LiveData<UiAction> getUiActionLiveData() {
        return _uiAction;
    }

    public void getUser() {
        int userId = AppPrefs.getCurrentUserId();
        User user = CoursesRepository.getUserById(userId);
        _user.postValue(user);
    }

    public void signOut() {
        AppPrefs.saveRememberMe(false);
        _uiAction.postValue(new NavigateToAuth());
    }

    public interface UiAction {}

    public static class NavigateToAuth implements UiAction {}
}
