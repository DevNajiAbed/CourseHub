package com.iug.coursehub.feature.dashboard.add_edit_lesson_screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.iug.coursehub.R;
import com.iug.coursehub.data.local.db.CoursesRepository;
import com.iug.coursehub.data.local.db.entity.Lesson;
import com.iug.coursehub.databinding.FragmentAddEditLessonBinding;
import com.iug.coursehub.feature.dashboard.lessons_screen.LessonsFragment;

public class AddEditLessonFragment extends Fragment {

    public static final String ARG_COURSE_ID = "course_id";
    public static final String ARG_LESSON_ID = "lesson_id";

    private Integer courseId;
    private Lesson lesson;
    private FragmentAddEditLessonBinding binding;

    private AddEditLessonViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AddEditLessonViewModel.class);

        if (getArguments() != null) {
            courseId = getArguments().getInt(ARG_COURSE_ID, 0);
            int lessonId = getArguments().getInt(ARG_LESSON_ID, 0);

            if (lessonId > 0) {
                lesson = CoursesRepository.getLessonById(lessonId);
                if (binding != null && lesson != null) {
                    fillFields(binding, lesson);
                }
            } else {
                viewModel.hideDeleteBtn();
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddEditLessonBinding.inflate(inflater, container, false);

        binding.btnNavigateUp.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt(LessonsFragment.ARG_COURSE_ID, courseId);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_addEditLessonFragment_to_lessonsFragment, bundle);
        });

        binding.btnDelete.setOnClickListener(v -> {
            if (lesson != null) {
                new MaterialAlertDialogBuilder(requireContext())
                        .setIcon(R.drawable.ic_delete)
                        .setTitle("Deleting Lesson")
                        .setMessage("Are you sure you want to delete this lesson?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            viewModel.deleteLesson(lesson);
                            dialog.dismiss();
                        })
                        .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                        .create()
                        .show();
            }
        });

        binding.saveButton.setOnClickListener(v -> {
            String title = binding.titleEditText.getText().toString().trim();
            String description = binding.descriptionEditText.getText().toString().trim();
            String ytLink = binding.ytLinkEditText.getText().toString().trim();

            Lesson lessonToSave = new Lesson(
                lesson != null ? lesson.getId() : null,
                title,
                description,
                ytLink,
                courseId
            );

            viewModel.saveLesson(lessonToSave);
        });

        viewModel.getUiAction().observe(getViewLifecycleOwner(), action -> {
            if (action instanceof AddEditLessonViewModel.UiAction.ShowToast) {
                Toast.makeText(requireContext(), ((AddEditLessonViewModel.UiAction.ShowToast) action).getMessage(), Toast.LENGTH_SHORT).show();
            } else if (action instanceof AddEditLessonViewModel.UiAction.HideDeleteBtn) {
                binding.btnDelete.setVisibility(View.GONE);
            } else if (action instanceof AddEditLessonViewModel.UiAction.NavigateUp) {
                Bundle bundle = new Bundle();
                bundle.putInt(LessonsFragment.ARG_COURSE_ID, courseId);
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_addEditLessonFragment_to_lessonsFragment, bundle);
            }
        });

        return binding.getRoot();
    }

    private void fillFields(FragmentAddEditLessonBinding binding, Lesson lesson) {
        binding.titleEditText.setText(lesson.getTitle());
        binding.descriptionEditText.setText(lesson.getDescription());
        binding.ytLinkEditText.setText(lesson.getYtLink());
    }
}