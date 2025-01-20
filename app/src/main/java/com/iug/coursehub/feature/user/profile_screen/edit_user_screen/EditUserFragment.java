package com.iug.coursehub.feature.user.profile_screen.edit_user_screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.iug.coursehub.R;
import com.iug.coursehub.data.local.db.entity.User;
import com.iug.coursehub.databinding.FragmentEditUserBinding;

public class EditUserFragment extends Fragment {

    private EditUserViewModel viewModel;
    private FragmentEditUserBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(EditUserViewModel.class);
        viewModel.getUser();

        binding = FragmentEditUserBinding.inflate(inflater, container, false);

        binding.btnNavigateUp.setOnClickListener(v -> 
            NavHostFragment.findNavController(this).navigate(R.id.action_editUserFragment_to_profileHomeFragment)
        );

        binding.updateAccountButton.setOnClickListener(v -> {
            String username = binding.usernameInput.getText().toString().trim();
            String email = binding.emailInput.getText().toString().trim();
            String password = binding.passwordInput.getText().toString().trim();

            if (validateFields(username, email, password)) {
                User user = new User(username, email, password);
                viewModel.updateUser(user);
            }
        });

        viewModel.getUserLiveData().observe(getViewLifecycleOwner(), user -> {
            if (user == null) return;
            binding.usernameInput.setText(user.getUsername());
            binding.emailInput.setText(user.getEmail());
            binding.passwordInput.setText(user.getPassword());
        });

        viewModel.getUiActionLiveData().observe(getViewLifecycleOwner(), action -> {
            if (action instanceof EditUserViewModel.NavigateUp) {
                NavHostFragment.findNavController(this).navigate(R.id.action_editUserFragment_to_profileHomeFragment);
            }
        });

        return binding.getRoot();
    }

    private boolean validateFields(String username, String email, String password) {
        if (username.isEmpty()) {
            Toast.makeText(requireContext(), "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (email.isEmpty()) {
            Toast.makeText(requireContext(), "Email is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.isEmpty()) {
            Toast.makeText(requireContext(), "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
