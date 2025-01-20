package com.iug.coursehub.feature.user.home_screen.course_categories_screen.course_list_screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.iug.coursehub.databinding.FragmentCourseListBinding;
import com.iug.coursehub.feature.user.home_screen.course_categories_screen.CourseCategoriesFragment;

public class CourseListFragment extends Fragment {

    private String category;
    private CourseListViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString(ARG_CATEGORY);
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        FragmentCourseListBinding binding = FragmentCourseListBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(this).get(CourseListViewModel.class);

        binding.rvCourses.setLayoutManager(new LinearLayoutManager(requireContext()));

        viewModel.getCourses(category).observe(getViewLifecycleOwner(), courses -> {
            if (courses.isEmpty()) {
                binding.rvCourses.setVisibility(View.GONE);
                binding.sorryMessage.setVisibility(View.VISIBLE);
            } else {
                binding.rvCourses.setVisibility(View.VISIBLE);
                binding.sorryMessage.setVisibility(View.GONE);

                CoursesAdapter adapter = new CoursesAdapter(
                        courses,
                        courseId -> CourseCategoriesFragment.viewModel.navigateToCourseDetails(courseId),
                        courseId -> viewModel.bookmarkCourse(courseId)
                );
                binding.rvCourses.setAdapter(adapter);
            }
        });

        viewModel.uiAction.observe(getViewLifecycleOwner(), action -> {
            if (action instanceof CourseListViewModel.UiAction.ShowToast) {
                Toast.makeText(requireContext(), ((CourseListViewModel.UiAction.ShowToast) action).getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }

    public static final String ARG_CATEGORY = "category";

    public static CourseListFragment newInstance(String category) {
        CourseListFragment fragment = new CourseListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }
}