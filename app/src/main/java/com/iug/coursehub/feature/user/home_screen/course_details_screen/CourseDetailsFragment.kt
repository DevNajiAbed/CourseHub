package com.iug.coursehub.feature.user.home_screen.course_details_screen

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.iug.coursehub.R
import com.iug.coursehub.data.local.db.entity.Course
import com.iug.coursehub.databinding.FragmentCourseDetailsBinding

class CourseDetailsFragment : Fragment() {

    private val viewModel: CourseDetailsViewModel by viewModels()

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
        val binding = FragmentCourseDetailsBinding.inflate(inflater, container, false)

        binding.apply {
            viewModel.getCourseDetails(courseId!!)

            viewModel.uiAction.observe(viewLifecycleOwner) { action ->
                when(action) {
                    is CourseDetailsViewModel.UiAction.CollectCourseDetails -> {
                        val courseDetails = action.courseDetails
                        this.fillFields(courseDetails)
                    }

                    is CourseDetailsViewModel.UiAction.ShowToast -> {
                        Toast.makeText(requireContext(), action.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            btnNavigateUp.setOnClickListener {
                findNavController().navigate(R.id.action_courseDetailsFragment_to_courseCategoriesFragment)
            }

            btnJoinCourse.setOnClickListener {
                viewModel.joinCourse(courseId!!)
            }
        }

        return binding.root
    }

    private fun FragmentCourseDetailsBinding.fillFields(courseDetails: CourseDetails) {
        val course = courseDetails.course
        val imgBitmap = BitmapFactory.decodeByteArray(course.imageByteArray, 0, course.imageByteArray.size)
        courseImage.setImageBitmap(imgBitmap)
        courseName.text = course.name
        coursePrice.text = "Price: $${course.price}"
        courseStudents.text = "Students Enrolled: ${courseDetails.noOfEnrolledStudents}"
        courseHours.text = "Total Hours: ${course.noOfHours}"
        courseContents.text = "The course contains ${courseDetails.noOfLessons} lessons.\nJoin the course to see them."
        courseLecturer.text = "Lecturer: [${course.lecturerName}]"
    }

    companion object {
        const val ARG_COURSE_ID = "course_id"
    }
}