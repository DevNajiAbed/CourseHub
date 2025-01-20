package com.iug.coursehub.feature.user.home_screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.iug.coursehub.databinding.FragmentUserHomeNavHostBinding;

public class UserHomeNavHostFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentUserHomeNavHostBinding binding = FragmentUserHomeNavHostBinding.inflate(inflater, container, false);



        return binding.getRoot();
    }
}
