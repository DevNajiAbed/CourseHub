package com.iug.coursehub.feature.user.my_courses_screen.lesson_content_screen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.iug.coursehub.R;
import com.iug.coursehub.data.local.db.entity.Lesson;
import com.iug.coursehub.databinding.FragmentLessonContentBinding;
import com.iug.coursehub.feature.user.my_courses_screen.lesson_list_screen.LessonListFragment;

public class LessonContentFragment extends Fragment {

    private final LessonContentViewModel viewModel = new LessonContentViewModel();

    private Integer courseId;
    private Integer lessonId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            courseId = getArguments().getInt(ARG_COURSE_ID, 0);
            lessonId = getArguments().getInt(ARG_LESSON_ID, 0);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel.getLesson(lessonId);

        FragmentLessonContentBinding binding = FragmentLessonContentBinding.inflate(inflater, container, false);

        viewModel.lessonContent.observe(getViewLifecycleOwner(), lesson -> {
            if (lesson == null) return;

            fillFields(binding, lesson);
        });

        Bundle bundle = new Bundle();
        bundle.putInt(LessonListFragment.ARG_COURSE_ID, courseId);
        binding.btnNavigateUp.setOnClickListener(v -> NavHostFragment.findNavController(LessonContentFragment.this)
                .navigate(
                        R.id.action_lessonContentFragment_to_lessonListFragment,
                        bundle
                ));

        return binding.getRoot();
    }

    private void fillFields(FragmentLessonContentBinding binding, Lesson lesson) {
        binding.tvLessonTitle.setText(lesson.getTitle());
        binding.tvLessonDescription.setText(lesson.getDescription());
        binding.btnOpenYTVideo.setOnClickListener(v -> {
            String link = lesson.getYtLink();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            startActivity(intent);
        });
    }

    public static final String ARG_COURSE_ID = "course_id";
    public static final String ARG_LESSON_ID = "lesson_id";
}
