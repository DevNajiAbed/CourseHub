package com.iug.coursehub.feature.user.home_screen.course_categories_screen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CourseCategoriesViewModel extends ViewModel {

    private final MutableLiveData<UiAction> _uiAction = new MutableLiveData<>();
    public final LiveData<UiAction> uiAction = _uiAction;

    public void navigateToCourseDetails(int courseId) {
        _uiAction.postValue(new UiAction.NavigateToCourseDetails(courseId));
    }

    public interface UiAction {
        class NavigateToCourseDetails implements UiAction {
            private final int courseId;

            public NavigateToCourseDetails(int courseId) {
                this.courseId = courseId;
            }

            public int getCourseId() {
                return courseId;
            }
        }
    }
}