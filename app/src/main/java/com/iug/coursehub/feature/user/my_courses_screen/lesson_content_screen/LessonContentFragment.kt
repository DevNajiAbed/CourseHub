package com.iug.coursehub.feature.user.my_courses_screen.lesson_content_screen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.iug.coursehub.R
import com.iug.coursehub.data.local.db.entity.Lesson
import com.iug.coursehub.databinding.FragmentLessonContentBinding
import com.iug.coursehub.feature.user.my_courses_screen.lesson_list_screen.LessonListFragment

class LessonContentFragment : Fragment() {

    private val viewModel by viewModels<LessonContentViewModel>()

    private var courseId: Int? = null
    private var lessonId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            courseId = it.getInt(ARG_COURSE_ID, 0)
            lessonId = it.getInt(ARG_LESSON_ID, 0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.getLesson(lessonId!!)

        val binding = FragmentLessonContentBinding.inflate(inflater, container, false)

        binding.apply {
            viewModel.lessonContent.observe(viewLifecycleOwner) { lesson ->
                if (lesson == null)
                    return@observe

                this.fillFields(lesson)
            }

            btnNavigateUp.setOnClickListener {
                findNavController().navigate(
                    R.id.action_lessonContentFragment_to_lessonListFragment,
                    Bundle().apply {
                        putInt(LessonListFragment.ARG_COURSE_ID, courseId!!)
                    }
                )
            }
        }

        return binding.root
    }

    private fun FragmentLessonContentBinding.fillFields(lesson: Lesson) {
        tvLessonTitle.text = lesson.title
        tvLessonDescription.text = lesson.description
        btnOpenYTVideo.setOnClickListener {
            val link = lesson.ytLink
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            startActivity(intent)
        }
    }

    companion object {
        const val ARG_COURSE_ID = "course_id"
        const val ARG_LESSON_ID = "lesson_id"
    }
}