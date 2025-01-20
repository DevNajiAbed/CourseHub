package com.iug.coursehub.feature.user.profile_screen.profile_home_screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.iug.coursehub.R;
import com.iug.coursehub.databinding.FragmentProfileHomeBinding;
import com.iug.coursehub.feature.user.UserNavHostFragment;

public class ProfileHomeFragment extends Fragment {

    private ProfileHomeViewModel viewModel;
    private FragmentProfileHomeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(ProfileHomeViewModel.class);
        viewModel.getUser();

        binding = FragmentProfileHomeBinding.inflate(inflater, container, false);

        binding.btnEditAccount.setOnClickListener(v -> 
            NavHostFragment.findNavController(this).navigate(R.id.action_profileHomeFragment_to_editUserFragment)
        );

        binding.btnShowBookmarkedCourses.setOnClickListener(v -> 
            NavHostFragment.findNavController(this).navigate(R.id.action_profileHomeFragment_to_bookmarkedCoursesFragment)
        );

        binding.btnSignOut.setOnClickListener(v -> viewModel.signOut());

        viewModel.getUserLiveData().observe(getViewLifecycleOwner(), user -> {
            if (user == null) return;
            binding.tvName.setText(user.getUsername());
            binding.tvEmail.setText(user.getEmail());
            binding.tvPassword.setText(user.getPassword());
        });

        viewModel.getUiActionLiveData().observe(getViewLifecycleOwner(), action -> {
            if (action instanceof ProfileHomeViewModel.NavigateToAuth) {
                UserNavHostFragment.viewModel.navigateFromUserToAuth();
            }
        });

        return binding.getRoot();
    }
}
