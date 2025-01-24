package com.iug.coursehub.feature.user.home_screen.cuorse_categories_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.iug.coursehub.R
import com.iug.coursehub.databinding.FragmentCourseCategoriesBinding
import com.iug.coursehub.feature.user.home_screen.course_details_screen.CourseDetailsFragment

class CourseCategoriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = viewModels<CourseCategoriesViewModel>().value

        val binding = FragmentCourseCategoriesBinding.inflate(inflater, container, false)

        binding.apply {
            val categoriesPagerAdapter = CategoriesPagerAdapter(
                requireActivity(),
                childFragmentManager
            )
            viewPager.adapter = categoriesPagerAdapter

            tabLayout.setupWithViewPager(viewPager)
        }

        viewModel.uiAction.observe(viewLifecycleOwner) { action ->
            when(action) {
                is CourseCategoriesViewModel.UiAction.NavigateToCourseDetails -> {
                    findNavController().navigate(
                        R.id.action_courseCategoriesFragment_to_courseDetailsFragment,
                        Bundle().apply {
                            putInt(CourseDetailsFragment.ARG_COURSE_ID, action.courseId)
                        }
                    )
                }
            }
        }

        return binding.root
    }

    companion object {
        lateinit var viewModel: CourseCategoriesViewModel
    }
}