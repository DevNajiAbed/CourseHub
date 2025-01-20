package com.iug.coursehub.feature.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.iug.coursehub.MainActivity;
import com.iug.coursehub.R;
import com.iug.coursehub.databinding.FragmentUserNavHostBinding;

public class UserNavHostFragment extends Fragment {

    private UserNavHostViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(UserNavHostViewModel.class);

        FragmentUserNavHostBinding binding = FragmentUserNavHostBinding.inflate(inflater, container, false);

        NavHostFragment navHostFragment = (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.user_nav_host);
        if (navHostFragment != null) {
            NavigationUI.setupWithNavController(binding.bottomNavigation, navHostFragment.getNavController());
        }

        viewModel.getUiAction().observe(getViewLifecycleOwner(), action -> {
            if (action instanceof UserNavHostViewModel.UiAction.NavigateFromUserToAuth) {
                ((MainActivity) requireActivity()).viewModel.navigateUserToAuth();
            }
        });

        return binding.getRoot();
    }
}