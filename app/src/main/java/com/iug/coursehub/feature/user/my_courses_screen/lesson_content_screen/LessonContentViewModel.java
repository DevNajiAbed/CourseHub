package com.iug.coursehub.feature.user.my_courses_screen.lesson_content_screen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iug.coursehub.data.local.db.CoursesRepository;
import com.iug.coursehub.data.local.db.entity.Lesson;

public class LessonContentViewModel extends ViewModel {

    private final MutableLiveData<Lesson> _lessonContent = new MutableLiveData<>();
    public LiveData<Lesson> lessonContent = _lessonContent;

    public void getLesson(int lessonId) {
        Lesson lesson = CoursesRepository.getLessonById(lessonId);
        _lessonContent.postValue(lesson);
    }
}
