package com.iug.coursehub.feature.user.profile_screen.bookmarked_courses_screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.iug.coursehub.databinding.FragmentBookmarkedCoursesBinding;

public class BookmarkedCoursesFragment extends Fragment {

    private BookmarkedCoursesViewModel viewModel;
    private FragmentBookmarkedCoursesBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(BookmarkedCoursesViewModel.class);
        viewModel.getBookmarkedCourses();

        binding = FragmentBookmarkedCoursesBinding.inflate(inflater, container, false);

        binding.rvCourses.setLayoutManager(new LinearLayoutManager(requireContext()));

        viewModel.getBookmarkedCoursesLiveData().observe(getViewLifecycleOwner(), courses -> {
            if (courses == null) {
                return;
            }
            binding.rvCourses.setAdapter(new CoursesAdapter(courses));
        });

        return binding.getRoot();
    }
}
