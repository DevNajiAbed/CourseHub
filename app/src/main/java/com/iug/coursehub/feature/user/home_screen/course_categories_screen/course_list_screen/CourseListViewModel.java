package com.iug.coursehub.feature.user.home_screen.course_categories_screen.course_list_screen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iug.coursehub.data.local.db.CoursesRepository;
import com.iug.coursehub.data.local.db.entity.Bookmarked;
import com.iug.coursehub.data.local.db.entity.Course;
import com.iug.coursehub.data.local.prefs.AppPrefs;

import java.util.List;

public class CourseListViewModel extends ViewModel {

    private final MutableLiveData<UiAction> _uiAction = new MutableLiveData<>();
    public LiveData<UiAction> uiAction = _uiAction;

    public LiveData<List<Course>> getCourses(String category) {
        return CoursesRepository.getCoursesByCategory(category);
    }

    public void bookmarkCourse(int courseId) {
        int userId = AppPrefs.getCurrentUserId();
        Bookmarked bookmarked = new Bookmarked(userId, courseId);
            CoursesRepository.upsertBookmarked(bookmarked);
            _uiAction.postValue(new UiAction.ShowToast("Bookmarked Successfully!"));
    }

    public interface UiAction {
        final class ShowToast implements UiAction {
            private final String message;

            public ShowToast(String message) {
                this.message = message;
            }

            public String getMessage() {
                return message;
            }
        }
    }
}
