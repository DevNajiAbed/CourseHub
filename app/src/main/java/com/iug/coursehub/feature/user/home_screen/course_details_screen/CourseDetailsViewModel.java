package com.iug.coursehub.feature.user.home_screen.course_details_screen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iug.coursehub.data.local.db.CoursesRepository;
import com.iug.coursehub.data.local.db.entity.Course;
import com.iug.coursehub.data.local.db.entity.Enrolled;
import com.iug.coursehub.data.local.prefs.AppPrefs;

public class CourseDetailsViewModel extends ViewModel {

    private final MutableLiveData<UiAction> _uiAction = new MutableLiveData<>();

    public LiveData<UiAction> getUiAction() {
        return _uiAction;
    }

    public void getCourseDetails(int courseId) {
        Course course = CoursesRepository.getCourseById(courseId);
        int noOfEnrolledStudents = CoursesRepository.getNoOfStudentsEnrolledByCourseId(courseId);
        int noOfLessons = CoursesRepository.getNoOfLessonsByCourseId(courseId);

        CourseDetails courseDetails = new CourseDetails(course, noOfEnrolledStudents, noOfLessons);
        _uiAction.postValue(new UiAction.CollectCourseDetails(courseDetails));
    }

    public void joinCourse(int courseId) {
        int userId = AppPrefs.getCurrentUserId();
        Enrolled enrolled = new Enrolled(userId, courseId);

        CoursesRepository.upsertEnrolled(enrolled);
        _uiAction.postValue(new UiAction.ShowToast("Joined Successfully!"));
    }

    public static abstract class UiAction {
        public static class CollectCourseDetails extends UiAction {
            private final CourseDetails courseDetails;

            public CollectCourseDetails(CourseDetails courseDetails) {
                this.courseDetails = courseDetails;
            }

            public CourseDetails getCourseDetails() {
                return courseDetails;
            }
        }

        public static class ShowToast extends UiAction {
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