package com.iug.coursehub.feature.dashboard.add_edit_course_screen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iug.coursehub.data.local.db.CoursesRepository;
import com.iug.coursehub.data.local.db.entity.Course;

public class AddEditCourseViewModel extends ViewModel {

    private final MutableLiveData<UiAction> _uiAction = new MutableLiveData<>();
    public LiveData<UiAction> uiAction = _uiAction;

    public void saveCourse(Course course) {
        if (validateFields(course)) {
            CoursesRepository.upsertCourse(course);
            _uiAction.postValue(new UiAction.NavigateUp());
        }
    }

    private boolean validateFields(Course course) {
        if (course.getImageByteArray() == null || course.getImageByteArray().length == 0) {
            _uiAction.postValue(new UiAction.ShowToast("You should pick an image"));
            return false;
        }
        if (course.getName() == null || course.getName().trim().isEmpty()) {
            _uiAction.postValue(new UiAction.ShowToast("You should insert a name for the course"));
            return false;
        }
        if (course.getPrice() <= 0) {
            _uiAction.postValue(new UiAction.ShowToast("You should insert a valid price"));
            return false;
        }
        if (course.getNoOfHours() <= 0) {
            _uiAction.postValue(new UiAction.ShowToast("You should insert a valid number of hours"));
            return false;
        }
        if (course.getLecturerName() == null || course.getLecturerName().trim().isEmpty()) {
            _uiAction.postValue(new UiAction.ShowToast("You should insert a name for the lecturer of the course"));
            return false;
        }
        if (course.getCategory() == null || course.getCategory().trim().isEmpty()) {
            _uiAction.postValue(new UiAction.ShowToast("You should pick a category for the course"));
            return false;
        }
        return true;
    }

    public static abstract class UiAction {
        public static class ShowToast extends UiAction {
            private final String message;

            public ShowToast(String message) {
                this.message = message;
            }

            public String getMessage() {
                return message;
            }
        }

        public static class NavigateUp extends UiAction {
        }
    }
}
