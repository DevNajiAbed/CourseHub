package com.iug.coursehub.feature.authentication;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.iug.coursehub.R;
import com.iug.coursehub.databinding.FragmentAuthNavHostBinding;

public class AuthNavHostFragment extends Fragment {

    public static AuthViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        FragmentAuthNavHostBinding binding = FragmentAuthNavHostBinding.inflate(inflater, container, false);

        viewModel.getUiAction().observe(getViewLifecycleOwner(), new Observer<AuthViewModel.UiAction>() {
            @Override
            public void onChanged(AuthViewModel.UiAction action) {
                switch (action) {
                    case NAVIGATE_TO_ADMIN_SCREEN:
                        findNavController(requireView()).navigate(R.id.action_authNavHostFragment_to_dashboardNavHostFragment);
                        break;
                    case NAVIGATE_TO_USER_SCREEN:
                        findNavController(requireView()).navigate(R.id.action_authNavHostFragment_to_userNavHostFragment);
                }
            }
        });

        return binding.getRoot();
    }
}
