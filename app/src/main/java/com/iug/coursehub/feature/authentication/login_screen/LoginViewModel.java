package com.iug.coursehub.feature.authentication.login_screen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iug.coursehub.data.local.db.CoursesRepository;
import com.iug.coursehub.data.local.db.entity.User;
import com.iug.coursehub.data.local.prefs.AppPrefs;

public class LoginViewModel extends ViewModel {

    private final MutableLiveData<UiAction> _uiAction = new MutableLiveData<>();
    private final LiveData<UiAction> uiAction = _uiAction;

    private boolean rememberMe = false;

    public LiveData<UiAction> getUiAction() {
        return uiAction;
    }

    public void saveRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public void login(
        String email,
        String password
    ) {
        _uiAction.postValue(new UiAction.Loading());
        if (validateFields(email, password)) {
            String adminEmail = AppPrefs.getAdminEmail();
            String adminPassword = AppPrefs.getAdminPassword();

            if (email.equals(adminEmail) && password.equals(adminPassword)) {
                AppPrefs.saveRememberMe(rememberMe);
                if (rememberMe) {
                    AppPrefs.saveCurrUserAsAdmin();
                }
                _uiAction.postValue(new UiAction.NavigateToAdminScreen());
                return;
            }

            User user = CoursesRepository.getUserByEmailAndPassword(email, password);

            if (user == null) {
                _uiAction.postValue(new UiAction.ShowToast("Error in email or password"));
                return;
            }

            if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                AppPrefs.saveRememberMe(rememberMe);
                AppPrefs.saveCurrentUserId(user.getId());
                _uiAction.postValue(new UiAction.NavigateToUserScreen());
            }
        }
    }

    private boolean validateFields(String... fields) {
        for (String field : fields) {
            if (field.isBlank()) {
                _uiAction.postValue(new UiAction.ShowToast("Please fill all fields"));
                return false;
            }
        }
        return true;
    }

    public interface UiAction {
        class ShowToast implements UiAction {

            private String message;

            public ShowToast(String message) {
                this.message = message;
            }


            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }
        }
        class NavigateToAdminScreen implements UiAction {}
        class NavigateToUserScreen implements UiAction {}
        class Loading implements UiAction {}
    }
}
