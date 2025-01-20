package com.iug.coursehub.feature.user.my_courses_screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.iug.coursehub.databinding.FragmentMyCoursesNavHostBinding;

public class MyCoursesNavHostFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        FragmentMyCoursesNavHostBinding binding = FragmentMyCoursesNavHostBinding.inflate(inflater, container, false);



        return binding.getRoot();
    }
}
