package com.iug.coursehub.feature.authentication.register_screen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iug.coursehub.data.local.db.CoursesRepository;
import com.iug.coursehub.data.local.db.entity.User;

public class RegisterViewModel extends ViewModel {

    private final MutableLiveData<UiAction> _uiAction = new MutableLiveData<>();
    private final LiveData<UiAction> uiAction = _uiAction;

    public LiveData<UiAction> getUiAction() {
        return uiAction;
    }

    public void signUp(
            String username,
            String email,
            String password
    ) {
        if (validateFields(username, email, password)) {
            User user = new User(username, email, password);
            CoursesRepository.upsertUser(user);
            _uiAction.postValue(new UiAction.NavigateUp());
        }
    }

    private boolean validateFields(String... fields) {
        for(String field: fields) {
            if (field.isBlank()) {
                _uiAction.postValue(new UiAction.ShowToast("All fields are required"));
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
        class NavigateUp implements UiAction {}
    }
}
