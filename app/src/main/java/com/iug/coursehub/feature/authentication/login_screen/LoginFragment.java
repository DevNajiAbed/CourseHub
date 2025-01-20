package com.iug.coursehub.feature.authentication.login_screen;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.iug.coursehub.R;
import com.iug.coursehub.databinding.FragmentLoginBinding;
import com.iug.coursehub.feature.authentication.AuthNavHostFragment;

public class LoginFragment extends Fragment {

    private LoginViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentLoginBinding binding = FragmentLoginBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding.rememberMeCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                viewModel.saveRememberMe(isChecked);
            }
        });
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.emailInput.getText().toString().trim();
                String password = binding.passwordInput.getText().toString().trim();
                viewModel.login(email, password);
            }
        });
        binding.createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findNavController(requireView()).navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });

        viewModel.getUiAction().observe(getViewLifecycleOwner(), new Observer<LoginViewModel.UiAction>() {
            @Override
            public void onChanged(LoginViewModel.UiAction action) {
                if (action instanceof LoginViewModel.UiAction.Loading) {
                    binding.progressBar.setVisibility(View.VISIBLE);
                } else if (action instanceof LoginViewModel.UiAction.NavigateToAdminScreen) {
                    AuthNavHostFragment.viewModel.navigateToAdminScreen();
                } else if (action instanceof LoginViewModel.UiAction.NavigateToUserScreen) {
                    AuthNavHostFragment.viewModel.navigateToUserScreen();
                } else if (action instanceof LoginViewModel.UiAction.ShowToast) {
                    LoginViewModel.UiAction.ShowToast showToast = (LoginViewModel.UiAction.ShowToast) action;
                    if (showToast.getMessage().toLowerCase().contains("error")) {
                        binding.progressBar.setVisibility(View.GONE);
                    }
                    Toast.makeText(requireContext(), showToast.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return binding.getRoot();
    }
}
