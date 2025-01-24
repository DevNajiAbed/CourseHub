package com.iug.coursehub.feature.user.home_screen.cuorse_categories_screen.course_list_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.iug.coursehub.databinding.FragmentCourseListBinding
import com.iug.coursehub.feature.user.home_screen.cuorse_categories_screen.CourseCategoriesFragment

class CourseListFragment : Fragment() {

    private var category: String? = null
    private val viewModel by viewModels<CourseListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getString(ARG_CATEGORY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCourseListBinding.inflate(inflater, container, false)

        binding.apply {
            rvCourses.apply {
                layoutManager = LinearLayoutManager(requireContext())

                val coursesLiveData = viewModel.getCourses(category!!)
                coursesLiveData.observe(viewLifecycleOwner) { courses ->
                    if(courses.isEmpty()) {
                        this.visibility = View.GONE
                        sorryMessage.visibility = View.VISIBLE
                    } else {
                        this.visibility = View.VISIBLE
                        sorryMessage.visibility = View.GONE

                        val adapter = CoursesAdapter(
                            courses = courses,
                            onShowLessonsClick = {
                                CourseCategoriesFragment.viewModel.navigateToCourseDetails(it)
                            },
                            onBookmarkClick = {
                                viewModel.bookmarkCourse(it)
                            }
                        )
                        this.adapter = adapter
                    }
                }
            }
        }

        viewModel.uiAction.observe(viewLifecycleOwner) { action ->
            when(action) {
                is CourseListViewModel.UiAction.ShowToast -> {
                    Toast.makeText(requireContext(), action.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        return binding.root
    }

    companion object {
        const val ARG_CATEGORY = "category"

        fun newInstance(category: String): CourseListFragment {
            return CourseListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CATEGORY, category)
                }
            }
        }
    }
}