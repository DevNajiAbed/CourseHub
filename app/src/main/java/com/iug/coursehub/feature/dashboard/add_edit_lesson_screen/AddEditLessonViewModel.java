package com.iug.coursehub.feature.dashboard.add_edit_lesson_screen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iug.coursehub.data.local.db.CoursesRepository;
import com.iug.coursehub.data.local.db.entity.Lesson;

public class AddEditLessonViewModel extends ViewModel {

    private final MutableLiveData<UiAction> _uiAction = new MutableLiveData<>();

    public LiveData<UiAction> getUiAction() {
        return _uiAction;
    }

    public void saveLesson(Lesson lesson) {
        if (validateFields(lesson)) {
            CoursesRepository.upsertLesson(lesson);
            _uiAction.postValue(new UiAction.NavigateUp());
        }
    }

    private boolean validateFields(Lesson lesson) {
        if (lesson.getTitle().trim().isEmpty()) {
            _uiAction.postValue(new UiAction.ShowToast("You should insert a title for the lesson"));
            return false;
        }
        if (lesson.getDescription().trim().isEmpty()) {
            _uiAction.postValue(new UiAction.ShowToast("You should insert a description for the lesson"));
            return false;
        }
        if (lesson.getYtLink().trim().isEmpty()) {
            _uiAction.postValue(new UiAction.ShowToast("You should insert a YouTube link for the lesson"));
            return false;
        }
        return true;
    }

    public void deleteLesson(Lesson lesson) {
        CoursesRepository.deleteLesson(lesson);
        _uiAction.postValue(new UiAction.NavigateUp());
    }

    public void hideDeleteBtn() {
        _uiAction.postValue(new UiAction.HideDeleteBtn());
    }

    public abstract static class UiAction {
        public static class ShowToast extends UiAction {
            private final String message;

            public ShowToast(String message) {
                this.message = message;
            }

            public String getMessage() {
                return message;
            }
        }

        public static class HideDeleteBtn extends UiAction {
        }

        public static class NavigateUp extends UiAction {
        }
    }
}
