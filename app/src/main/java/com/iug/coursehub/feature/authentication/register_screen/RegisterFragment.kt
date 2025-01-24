package com.iug.coursehub.feature.authentication.register_screen

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
import com.iug.coursehub.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentRegisterBinding.inflate(inflater, container, false)

        binding.apply {
            loginButton.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }

            createAccountButton.setOnClickListener {
                val username = usernameInput.text.toString().trim()
                val email = emailInput.text.toString().trim()
                val password = passwordInput.text.toString().trim()
                viewModel.signUp(
                    username = username,
                    email = email,
                    password = password
                )
            }
        }

        viewModel.uiAction.observe(viewLifecycleOwner) { action ->
            when(action) {
                is RegisterViewModel.UiAction.ShowToast -> {
                    Toast.makeText(requireContext(), action.message, Toast.LENGTH_SHORT).show()
                }
                RegisterViewModel.UiAction.NavigateUp -> {
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                }
            }
        }

        return binding.root
    }
}