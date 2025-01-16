package com.iug.coursehub.feature.user.my_courses_screen.lesson_list_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.iug.coursehub.R
import com.iug.coursehub.data.local.db.CoursesRepository
import com.iug.coursehub.databinding.FragmentLessonListBinding
import com.iug.coursehub.feature.user.my_courses_screen.lesson_content_screen.LessonContentFragment

class LessonListFragment : Fragment() {

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
        val binding = FragmentLessonListBinding.inflate(inflater, container, false)

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
                                    R.id.action_lessonListFragment_to_lessonContentFragment,
                                    Bundle().apply {
                                        putInt(LessonContentFragment.ARG_COURSE_ID, courseId!!)
                                        putInt(LessonContentFragment.ARG_LESSON_ID, it)
                                    }
                                )
                            }
                        )
                        this.adapter = adapter
                    }
                }
            }

            btnNavigateUp.setOnClickListener {
                findNavController().navigate(R.id.action_lessonListFragment_to_myCourseListFragment,)
            }
        }

        return binding.root
    }

    companion object {
        const val ARG_COURSE_ID = "course_id"
    }
}