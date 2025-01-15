package com.iug.coursehub.feature.dashboard.courses_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.iug.coursehub.R
import com.iug.coursehub.data.local.db.CoursesRepository
import com.iug.coursehub.databinding.FragmentCoursesBinding
import com.iug.coursehub.feature.dashboard.DashboardNavHostFragment
import com.iug.coursehub.feature.dashboard.add_edit_course_screen.AddEditCourseFragment
import com.iug.coursehub.feature.dashboard.lessons_screen.LessonsFragment

class CoursesFragment : Fragment() {

    private val viewModel by viewModels<CoursesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCoursesBinding.inflate(inflater, container, false)

        binding.apply {
            rvCourses.apply {
                layoutManager = LinearLayoutManager(requireContext())

                val allCoursesLiveData = CoursesRepository.getAllCourses()
                allCoursesLiveData.observe(viewLifecycleOwner) { allCourses ->
                    if (allCourses.isEmpty()) {
                        content.visibility = View.GONE
                        sorryMessage.visibility = View.VISIBLE
                    } else {
                        content.visibility = View.VISIBLE
                        sorryMessage.visibility = View.GONE

                        val adapter = CoursesAdapter(
                            courses = allCourses,
                            onShowLessonsClick = {
                                findNavController().navigate(
                                    R.id.action_coursesFragment_to_lessonsFragment,
                                    Bundle().apply {
                                        putInt(LessonsFragment.ARG_COURSE_ID, it)
                                    }
                                )
                            },
                            onEditCourseClick = {
                                findNavController().navigate(
                                    R.id.action_coursesFragment_to_addEditCourseFragment,
                                    Bundle().apply {
                                        putInt(AddEditCourseFragment.ARG_COURSE_ID, it)
                                    }
                                )
                            },
                            onDeleteCourseClick = {
                                MaterialAlertDialogBuilder(requireContext())
                                    .setIcon(R.drawable.ic_delete)
                                    .setTitle("Deleting Course")
                                    .setMessage("Are you sure you want to delete this course?")
                                    .setPositiveButton("Yes") { dialogInterface, _ ->
                                        viewModel.deleteCourse(it)
                                        dialogInterface.dismiss()
                                    }.setNegativeButton("No") { dialogInterface, _ ->
                                        dialogInterface.dismiss()
                                    }.create()
                                    .show()
                            }
                        )
                        this.adapter = adapter
                    }
                }
            }

            fabAddCourse.setOnClickListener {
                findNavController().navigate(R.id.action_coursesFragment_to_addEditCourseFragment)
            }

            btnSignOut.setOnClickListener {
                viewModel.signOut()
            }
        }

        viewModel.uiAction.observe(viewLifecycleOwner) { action ->
            when(action) {
                CoursesViewModel.UiAction.NavigateToAuthScreen -> {
                    DashboardNavHostFragment.viewModel.navigateToAuthScreen()
                }
            }
        }

        return binding.root
    }
}