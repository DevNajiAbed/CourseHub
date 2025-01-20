package com.iug.coursehub.feature.user.home_screen.course_categories_screen;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.iug.coursehub.R;
import com.iug.coursehub.databinding.FragmentCourseCategoriesBinding;
import com.iug.coursehub.feature.user.home_screen.course_details_screen.CourseDetailsFragment;

public class CourseCategoriesFragment extends Fragment {

    public static CourseCategoriesViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(CourseCategoriesViewModel.class);

        FragmentCourseCategoriesBinding binding = FragmentCourseCategoriesBinding.inflate(inflater, container, false);

        CategoriesPagerAdapter categoriesPagerAdapter = new CategoriesPagerAdapter(
                requireActivity(),
                getChildFragmentManager()
        );
        binding.viewPager.setAdapter(categoriesPagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        viewModel.uiAction.observe(getViewLifecycleOwner(), action -> {
            if (action instanceof CourseCategoriesViewModel.UiAction.NavigateToCourseDetails) {
                CourseCategoriesViewModel.UiAction.NavigateToCourseDetails navigateAction =
                        (CourseCategoriesViewModel.UiAction.NavigateToCourseDetails) action;

                Bundle bundle = new Bundle();
                bundle.putInt(CourseDetailsFragment.ARG_COURSE_ID, navigateAction.getCourseId());

                findNavController(requireView()).navigate(
                    R.id.action_courseCategoriesFragment_to_courseDetailsFragment,
                    bundle
                );
            }
        });

        return binding.getRoot();
    }
}
