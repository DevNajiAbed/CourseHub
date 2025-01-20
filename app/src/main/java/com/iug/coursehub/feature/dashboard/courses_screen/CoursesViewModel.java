package com.iug.coursehub.feature.dashboard.courses_screen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iug.coursehub.data.local.db.CoursesRepository;
import com.iug.coursehub.data.local.db.entity.Course;
import com.iug.coursehub.data.local.prefs.AppPrefs;

public class CoursesViewModel extends ViewModel {

    private final MutableLiveData<UiAction> _uiAction = new MutableLiveData<>();
    private final LiveData<UiAction> uiAction = _uiAction;

    public LiveData<UiAction> getUiAction() {
        return uiAction;
    }

    public void deleteCourse(Course course) {
        CoursesRepository.deleteCourse(course);
    }

    public void signOut() {
        AppPrefs.saveRememberMe(false);
        AppPrefs.unSaveCurrUserAsAdmin();
        _uiAction.postValue(UiAction.NAVIGATE_TO_AUTH_SCREEN);
    }

    public enum UiAction {
        NAVIGATE_TO_AUTH_SCREEN
    }
}
