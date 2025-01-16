package com.iug.coursehub.feature.user.my_courses_screen.lesson_content_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iug.coursehub.data.local.db.CoursesRepository
import com.iug.coursehub.data.local.db.entity.Lesson
import kotlinx.coroutines.launch

class LessonContentViewModel : ViewModel() {

    private val _lessonContent = MutableLiveData<Lesson>()
    val lessonContent: LiveData<Lesson> = _lessonContent

    fun getLesson(lessonId: Int) {
        viewModelScope.launch {
            val lesson = CoursesRepository.getLessonById(lessonId)
            _lessonContent.postValue(lesson)
        }
    }
}