package com.iug.coursehub.feature.authentication.register_screen;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.iug.coursehub.R;
import com.iug.coursehub.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentRegisterBinding binding = FragmentRegisterBinding.inflate(inflater, container, false);

        RegisterViewModel viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findNavController(requireView()).navigate(R.id.action_registerFragment_to_loginFragment);
            }
        });

        binding.createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.usernameInput.getText().toString().trim();
                String email = binding.emailInput.getText().toString().trim();
                String password = binding.passwordInput.getText().toString().trim();
                viewModel.signUp(username, email, password);
            }
        });

        viewModel.getUiAction().observe(getViewLifecycleOwner(), new Observer<RegisterViewModel.UiAction>() {
            @Override
            public void onChanged(RegisterViewModel.UiAction action) {
                if(action instanceof RegisterViewModel.UiAction.ShowToast) {
                    RegisterViewModel.UiAction.ShowToast showToast = (RegisterViewModel.UiAction.ShowToast) action;
                    Toast.makeText(requireContext(), showToast.getMessage(), Toast.LENGTH_SHORT).show();
                } else if(action instanceof RegisterViewModel.UiAction.NavigateUp) {
                    findNavController(requireView()).navigate(R.id.action_registerFragment_to_loginFragment);
                }
            }
        });

        return binding.getRoot();
    }
}
