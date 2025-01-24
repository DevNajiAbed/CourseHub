package com.iug.coursehub.feature.user.profile_screen.bookmarked_courses_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.iug.coursehub.R
import com.iug.coursehub.databinding.FragmentBookmarkedCoursesBinding

class BookmarkedCoursesFragment : Fragment() {

    private val viewModel by viewModels<BookmarkedCoursesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.getBookmarkedCourses()

        val binding = FragmentBookmarkedCoursesBinding.inflate(inflater, container, false)

        binding.apply {
            rvCourses.apply {
                layoutManager = LinearLayoutManager(requireContext())

                viewModel.bookmarkedCourses.observe(viewLifecycleOwner) { courses ->
                    if(courses == null)
                        return@observe

                    adapter = CoursesAdapter(courses)
                }
            }
        }

        return binding.root
    }
}