package com.iug.coursehub.feature.dashboard.add_edit_course_screen

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.iug.coursehub.R
import com.iug.coursehub.data.local.db.CoursesRepository
import com.iug.coursehub.data.local.db.entity.Course
import com.iug.coursehub.databinding.FragmentAddEditCourseBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.UUID

class AddEditCourseFragment : Fragment() {
    private lateinit var binding: FragmentAddEditCourseBinding

    private lateinit var mediaPickerLauncher: ActivityResultLauncher<PickVisualMediaRequest>
    private var imageByteArray: ByteArray? = null

    private val viewModel by viewModels<AddEditCourseViewModel>()
    private var course: Course? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val courseId = it.getInt(ARG_COURSE_ID, 0)
            if (courseId > 0)
                lifecycleScope.launch {
                    course = CoursesRepository.getCourseById(courseId)
                    imageByteArray = course!!.imageByteArray
                    binding.fillFields(course!!)
                }
        }

        mediaPickerLauncher = registerForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia()
        ) { uri ->
            if (uri != null) {
                imageByteArray = uri.toByteArray()
                binding.courseImageView.setImageURI(uri)
            } else {
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddEditCourseBinding.inflate(inflater, container, false)

        binding.apply {
            btnNavigateUp.setOnClickListener {
                findNavController().navigate(R.id.action_addEditCourseFragment_to_coursesFragment)
            }

            selectImageButton.setOnClickListener {
                mediaPickerLauncher.launch(
                    PickVisualMediaRequest(
                        mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            }

            var selectedCategory = ""
            spinnerCategories.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position == 0)
                        selectedCategory = ""
                    else
                        selectedCategory = parent.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }

            saveButton.setOnClickListener {
                this@AddEditCourseFragment.imageByteArray?.let {
                    val courseName = nameEditText.text.toString().trim()
                    val price = if (priceEditText.text.toString().isBlank()) {
                        Toast.makeText(
                            requireContext(),
                            "Price field shouldn't be empty",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@setOnClickListener
                    } else
                        priceEditText.text.toString().toFloat()
                    val noOfHours = if (hoursEditText.text.toString().isBlank()) {
                        Toast.makeText(
                            requireContext(),
                            "Number of hours' field shouldn't be empty",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@setOnClickListener
                    } else
                        hoursEditText.text.toString().toInt()
                    val lecturerName = lecturerEditText.text.toString().trim()

                    val course = Course(
                        id = if (this@AddEditCourseFragment.course != null)
                            this@AddEditCourseFragment.course!!.id
                        else
                            null,
                        name = courseName,
                        price = price,
                        noOfHours = noOfHours,
                        lecturerName = lecturerName,
                        imageByteArray = it,
                        category = selectedCategory
                    )

                    viewModel.saveCourse(course)
                }
            }
        }

        viewModel.uiAction.observe(viewLifecycleOwner) { action ->
            when (action) {
                is AddEditCourseViewModel.UiAction.ShowToast -> {
                    Toast.makeText(requireContext(), action.message, Toast.LENGTH_SHORT).show()
                }

                AddEditCourseViewModel.UiAction.NavigateUp -> {
                    findNavController().navigate(R.id.action_addEditCourseFragment_to_coursesFragment)
                }
            }
        }

        return binding.root
    }

    private fun FragmentAddEditCourseBinding.fillFields(course: Course) {
        val bitmap =
            BitmapFactory.decodeByteArray(course.imageByteArray, 0, course.imageByteArray.size)
        courseImageView.setImageBitmap(bitmap)
        nameEditText.setText(course.name)
        priceEditText.setText(course.price.toString())
        hoursEditText.setText(course.noOfHours.toString())
        lecturerEditText.setText(course.lecturerName)
    }

    private fun Uri.toByteArray(): ByteArray? {
        val inputStream = requireContext().contentResolver.openInputStream(this)
        return inputStream?.readBytes()
    }

    companion object {
        const val ARG_COURSE_ID = "course_id"
    }
}