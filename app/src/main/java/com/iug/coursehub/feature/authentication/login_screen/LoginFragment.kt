package com.iug.coursehub.feature.authentication.login_screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.iug.coursehub.R
import com.iug.coursehub.databinding.FragmentLoginBinding
import com.iug.coursehub.feature.authentication.AuthNavHostFragment

class LoginFragment : Fragment() {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.apply {
            rememberMeCheckbox.setOnCheckedChangeListener { _, isChecked ->
                viewModel.saveRememberMe(isChecked)
            }
            loginButton.setOnClickListener {
                val email = binding.emailInput.text.toString().trim()
                val password = binding.passwordInput.text.toString().trim()
                viewModel.login(email, password)
            }
            createAccountButton.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }

        viewModel.uiAction.observe(viewLifecycleOwner) { action ->
            when (action) {
                LoginViewModel.UiAction.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                LoginViewModel.UiAction.NavigateToAdminScreen -> {
                    AuthNavHostFragment.viewModel.navigateToAdminScreen()
                }

                LoginViewModel.UiAction.NavigateToUserScreen -> {
                    AuthNavHostFragment.viewModel.navigateToUserScreen()
                }

                is LoginViewModel.UiAction.ShowToast -> {
                    if(action.message.lowercase().contains("error")) {
                        binding.progressBar.visibility = View.GONE
                    }
                    Toast.makeText(requireContext(), action.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        return binding.root
    }
}