package com.iug.coursehub.feature.dashboard.lessons_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.iug.coursehub.R
import com.iug.coursehub.data.local.db.CoursesRepository
import com.iug.coursehub.databinding.FragmentLessonsBinding
import com.iug.coursehub.feature.dashboard.add_edit_course_screen.AddEditCourseFragment
import com.iug.coursehub.feature.dashboard.add_edit_lesson_screen.AddEditLessonFragment
import com.iug.coursehub.feature.dashboard.courses_screen.CoursesAdapter

class LessonsFragment : Fragment() {

    private var courseId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            courseId = it.getInt(ARG_COURSE_ID, 0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLessonsBinding.inflate(inflater, container, false)

        binding.apply {
            rvLessons.apply {
                layoutManager = LinearLayoutManager(requireContext())

                val allLessonsLiveData = CoursesRepository.getAllLessonsOfCourse(courseId!!)
                allLessonsLiveData.observe(viewLifecycleOwner) { allLessons ->
                    if (allLessons.isEmpty()) {
                        content.visibility = View.GONE
                        sorryMessage.visibility = View.VISIBLE
                    } else {
                        content.visibility = View.VISIBLE
                        sorryMessage.visibility = View.GONE

                        val adapter = LessonsAdapter(
                            lessons = allLessons,
                            onShowContentClick = {
                                findNavController().navigate(
                                    R.id.action_lessonsFragment_to_addEditLessonFragment,
                                    Bundle().apply {
                                        putInt(AddEditLessonFragment.ARG_COURSE_ID, courseId!!)
                                        putInt(AddEditLessonFragment.ARG_LESSON_ID, it)
                                    }
                                )
                            }
                        )
                        this.adapter = adapter
                    }
                }
            }

            btnNavigateUp.setOnClickListener {
                findNavController().navigate(R.id.action_lessonsFragment_to_coursesFragment)
            }

            fabAddCourse.setOnClickListener {
                findNavController().navigate(
                    R.id.action_lessonsFragment_to_addEditLessonFragment,
                    Bundle().apply {
                        putInt(AddEditLessonFragment.ARG_COURSE_ID, courseId!!)
                    }
                )
            }
        }

        return binding.root
    }

    companion object {
        const val ARG_COURSE_ID = "courseId"
    }
}