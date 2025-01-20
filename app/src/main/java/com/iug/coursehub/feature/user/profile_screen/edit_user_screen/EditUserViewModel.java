package com.iug.coursehub.feature.user.profile_screen.edit_user_screen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iug.coursehub.data.local.db.CoursesRepository;
import com.iug.coursehub.data.local.db.entity.User;
import com.iug.coursehub.data.local.prefs.AppPrefs;

public class EditUserViewModel extends ViewModel {

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

    public void updateUser(User user) {
        int userId = AppPrefs.getCurrentUserId();
        user.setId(userId);
        CoursesRepository.upsertUser(user);
        _uiAction.postValue(new NavigateUp());
    }

    public interface UiAction {}

    public static class NavigateUp implements UiAction {}
}
