package com.iug.coursehub.feature.user.my_courses_screen.lesson_list_screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.iug.coursehub.R;
import com.iug.coursehub.data.local.db.CoursesRepository;
import com.iug.coursehub.databinding.FragmentLessonListBinding;
import com.iug.coursehub.feature.user.my_courses_screen.lesson_content_screen.LessonContentFragment;

public class LessonListFragment extends Fragment {

    private Integer courseId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            courseId = getArguments().getInt(ARG_COURSE_ID, 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentLessonListBinding binding = FragmentLessonListBinding.inflate(inflater, container, false);

        binding.rvLessons.setLayoutManager(new LinearLayoutManager(requireContext()));

        CoursesRepository.getAllLessonsOfCourse(courseId).observe(getViewLifecycleOwner(), allLessons -> {
            if (allLessons.isEmpty()) {
                binding.content.setVisibility(View.GONE);
                binding.sorryMessage.setVisibility(View.VISIBLE);
            } else {
                binding.content.setVisibility(View.VISIBLE);
                binding.sorryMessage.setVisibility(View.GONE);

                LessonsAdapter adapter = new LessonsAdapter(
                        allLessons,
                        lessonId -> {
                            Bundle bundle = new Bundle();
                            bundle.putInt(LessonContentFragment.ARG_COURSE_ID, courseId);
                            bundle.putInt(LessonContentFragment.ARG_LESSON_ID, lessonId);
                            NavHostFragment.findNavController(LessonListFragment.this)
                                    .navigate(
                                            R.id.action_lessonListFragment_to_lessonContentFragment,
                                            bundle
                                    );
                        }
                );
                binding.rvLessons.setAdapter(adapter);
            }
        });

        binding.btnNavigateUp.setOnClickListener(v ->
                NavHostFragment.findNavController(LessonListFragment.this)
                        .navigate(R.id.action_lessonListFragment_to_myCourseListFragment));

        return binding.getRoot();
    }

    public static final String ARG_COURSE_ID = "course_id";
}