package com.iug.coursehub.feature.user.my_courses_screen.my_course_list_screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.iug.coursehub.R;
import com.iug.coursehub.data.local.db.entity.Course;
import com.iug.coursehub.databinding.FragmentMyCourseListBinding;
import com.iug.coursehub.feature.user.my_courses_screen.lesson_list_screen.LessonListFragment;

import java.util.List;

public class MyCourseListFragment extends Fragment {

    private final MyCoursesViewModel viewModel = new MyCoursesViewModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentMyCourseListBinding binding = FragmentMyCourseListBinding.inflate(inflater, container, false);
        viewModel.getEnrolledCourses();

        binding.rvCourses.setLayoutManager(new LinearLayoutManager(requireContext()));

        viewModel.courses.observe(getViewLifecycleOwner(), new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                if (courses == null)
                    return;

                if (courses.isEmpty()) {
                    binding.content.setVisibility(View.GONE);
                    binding.sorryMessage.setVisibility(View.VISIBLE);
                } else {
                    binding.content.setVisibility(View.VISIBLE);
                    binding.sorryMessage.setVisibility(View.GONE);

                    CoursesAdapter adapter = new CoursesAdapter(
                            courses,
                            new CoursesAdapter.OnShowLessonsClickListener() {
                                @Override
                                public void onClick(int courseId) {
                                    Bundle args = new Bundle();
                                    args.putInt(LessonListFragment.ARG_COURSE_ID, courseId);
                                    NavHostFragment.findNavController(MyCourseListFragment.this)
                                            .navigate(R.id.action_myCourseListFragment_to_lessonListFragment, args);
                                }
                            }
                    );
                    binding.rvCourses.setAdapter(adapter);
                }
            }
        });

        return binding.getRoot();
    }
}