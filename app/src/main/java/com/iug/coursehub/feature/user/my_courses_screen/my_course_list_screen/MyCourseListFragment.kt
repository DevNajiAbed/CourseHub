package com.iug.coursehub.feature.user.my_courses_screen.my_course_list_screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.iug.coursehub.R
import com.iug.coursehub.databinding.FragmentMyCourseListBinding
import com.iug.coursehub.feature.user.my_courses_screen.lesson_list_screen.LessonListFragment

class MyCourseListFragment : Fragment() {

    private val viewModel by viewModels<MyCoursesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMyCourseListBinding.inflate(inflater, container, false)
        viewModel.getEnrolledCourses()

        binding.apply {
            rvCourses.apply {
                layoutManager = LinearLayoutManager(requireContext())

                viewModel.courses.observe(viewLifecycleOwner) { courses ->
                    if(courses == null)
                        return@observe

                    if (courses.isEmpty()) {
                        content.visibility = View.GONE
                        sorryMessage.visibility = View.VISIBLE
                    } else {
                        content.visibility = View.VISIBLE
                        sorryMessage.visibility = View.GONE

                        val adapter = CoursesAdapter(
                            courses = courses,
                            onShowLessonsClick = {
                                findNavController().navigate(
                                    R.id.action_myCourseListFragment_to_lessonListFragment,
                                    Bundle().apply {
                                        putInt(LessonListFragment.ARG_COURSE_ID, it)
                                    }
                                )
                            }
                        )
                        this.adapter = adapter
                    }
                }
            }
        }

        return binding.root
    }
}