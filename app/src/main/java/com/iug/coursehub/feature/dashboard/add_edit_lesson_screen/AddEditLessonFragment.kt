package com.iug.coursehub.feature.dashboard.add_edit_lesson_screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.iug.coursehub.R
import com.iug.coursehub.data.local.db.CoursesRepository
import com.iug.coursehub.data.local.db.entity.Lesson
import com.iug.coursehub.databinding.FragmentAddEditLessonBinding
import com.iug.coursehub.feature.dashboard.lessons_screen.LessonsFragment
import kotlinx.coroutines.launch

class AddEditLessonFragment : Fragment() {

    private var courseId: Int? = null
    private var lesson: Lesson? = null
    private lateinit var binding: FragmentAddEditLessonBinding

    private val viewModel by viewModels<AddEditLessonViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            courseId = it.getInt(ARG_COURSE_ID, 0)

            val lessonId = it.getInt(ARG_LESSON_ID, 0)
            if (lessonId > 0)
                lifecycleScope.launch {
                    lesson = CoursesRepository.getLessonById(lessonId)
                    binding.fillFields(lesson!!)
                }
            else
                viewModel.hideDeleteBtn()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddEditLessonBinding.inflate(inflater, container, false)

        binding.apply {
            btnNavigateUp.setOnClickListener {
                findNavController().navigate(
                    R.id.action_addEditLessonFragment_to_lessonsFragment,
                    Bundle().apply {
                        putInt(LessonsFragment.ARG_COURSE_ID, courseId!!)
                    }
                )
            }

            btnDelete.setOnClickListener {
                if (this@AddEditLessonFragment.lesson != null) {
                    MaterialAlertDialogBuilder(requireContext())
                        .setIcon(R.drawable.ic_delete)
                        .setTitle("Deleting Lesson")
                        .setMessage("Are you sure you want to delete this lesson?")
                        .setPositiveButton("Yes") { dialogInterface, _ ->
                            viewModel.deleteLesson(this@AddEditLessonFragment.lesson!!)
                            dialogInterface.dismiss()
                        }.setNegativeButton("No") { dialogInterface, _ ->
                            dialogInterface.dismiss()
                        }.create()
                        .show()
                }
            }

            saveButton.setOnClickListener {
                val title = titleEditText.text.toString().trim()
                val description = descriptionEditText.text.toString().trim()
                val ytLink = ytLinkEditText.text.toString().trim()

                val lesson = Lesson(
                    id = if (this@AddEditLessonFragment.lesson != null)
                        this@AddEditLessonFragment.lesson!!.id
                    else
                        null,
                    title = title,
                    description = description,
                    ytLink = ytLink,
                    courseId = courseId!!
                )

                viewModel.saveLesson(lesson)
            }
        }

        viewModel.uiAction.observe(viewLifecycleOwner) { action ->
            when (action) {
                is AddEditLessonViewModel.UiAction.ShowToast -> {
                    Toast.makeText(requireContext(), action.message, Toast.LENGTH_SHORT).show()
                }

                AddEditLessonViewModel.UiAction.HideDeleteBtn -> {
                    binding.btnDelete.visibility = View.GONE
                }

                AddEditLessonViewModel.UiAction.NavigateUp -> {
                    findNavController().navigate(
                        R.id.action_addEditLessonFragment_to_lessonsFragment,
                        Bundle().apply {
                            putInt(LessonsFragment.ARG_COURSE_ID, courseId!!)
                        }
                    )
                }
            }
        }

        return binding.root
    }

    private fun FragmentAddEditLessonBinding.fillFields(lesson: Lesson) {
        lesson.apply {
            titleEditText.setText(title)
            descriptionEditText.setText(description)
            ytLinkEditText.setText(ytLink)
        }
    }

    companion object {
        const val ARG_COURSE_ID = "course_id"
        const val ARG_LESSON_ID = "lesson_id"
    }
}