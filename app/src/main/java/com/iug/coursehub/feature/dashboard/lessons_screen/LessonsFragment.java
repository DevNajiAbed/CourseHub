package com.iug.coursehub.feature.dashboard.lessons_screen;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.iug.coursehub.R;
import com.iug.coursehub.data.local.db.CoursesRepository;
import com.iug.coursehub.data.local.db.entity.Lesson;
import com.iug.coursehub.databinding.FragmentLessonsBinding;
import com.iug.coursehub.feature.dashboard.add_edit_lesson_screen.AddEditLessonFragment;

import java.util.List;

public class LessonsFragment extends Fragment {

    public static final String ARG_COURSE_ID = "courseId";

    private Integer courseId = null;
    private FragmentLessonsBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            courseId = getArguments().getInt(ARG_COURSE_ID, 0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLessonsBinding.inflate(inflater, container, false);

        binding.rvLessons.setLayoutManager(new LinearLayoutManager(requireContext()));

        CoursesRepository.getAllLessonsOfCourse(courseId).observe(getViewLifecycleOwner(), new Observer<List<Lesson>>() {
            @Override
            public void onChanged(List<Lesson> allLessons) {
                if (allLessons.isEmpty()) {
                    binding.content.setVisibility(View.GONE);
                    binding.sorryMessage.setVisibility(View.VISIBLE);
                } else {
                    binding.content.setVisibility(View.VISIBLE);
                    binding.sorryMessage.setVisibility(View.GONE);

                    LessonsAdapter adapter = new LessonsAdapter(allLessons, new OnLessonClick() {
                        @Override
                        public void onLessonClick(int lessonId) {
                            Bundle args = new Bundle();
                            args.putInt(AddEditLessonFragment.ARG_COURSE_ID, courseId);
                            NavHostFragment.findNavController(LessonsFragment.this).navigate(
                                    R.id.action_lessonsFragment_to_addEditLessonFragment,
                                    args
                            );
                        }
                    });
                    binding.rvLessons.setAdapter(adapter);
                }
            }
        });

        binding.btnNavigateUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(LessonsFragment.this).navigate(R.id.action_lessonsFragment_to_coursesFragment);
            }
        });

        binding.fabAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putInt(AddEditLessonFragment.ARG_COURSE_ID, courseId);
                NavHostFragment.findNavController(LessonsFragment.this).navigate(
                        R.id.action_lessonsFragment_to_addEditLessonFragment,
                        args
                );
            }
        });

        return binding.getRoot();
    }
}