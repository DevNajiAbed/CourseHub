package com.iug.coursehub.feature.user.profile_screen.edit_user_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.iug.coursehub.R
import com.iug.coursehub.data.local.db.entity.User
import com.iug.coursehub.databinding.FragmentEditUserBinding

class EditUserFragment : Fragment() {

    private val viewModel by viewModels<EditUserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.getUser()

        val binding = FragmentEditUserBinding.inflate(inflater, container, false)

        binding.apply {
            viewModel.user.observe(viewLifecycleOwner) { user ->
                if(user == null)
                    return@observe

                usernameInput.setText(user.username)
                emailInput.setText(user.email)
                passwordInput.setText(user.password)
            }

            viewModel.uiAction.observe(viewLifecycleOwner) { action ->
                when(action) {
                    EditUserViewModel.UiAction.NavigateUp -> {
                        findNavController().navigate(R.id.action_editUserFragment_to_profileHomeFragment)
                    }
                }
            }

            updateAccountButton.setOnClickListener {
                val username = usernameInput.text.toString().trim()
                val email = emailInput.text.toString().trim()
                val password = passwordInput.text.toString().trim()

                if(validateFields(username, email, password)) {
                    val user = User(
                        username = username,
                        email = email,
                        password = password
                    )

                    viewModel.updateUser(user)
                }
            }

            btnNavigateUp.setOnClickListener {
                findNavController().navigate(R.id.action_editUserFragment_to_profileHomeFragment)
            }
        }

        return binding.root
    }

    private fun validateFields(username: String, email: String, password: String): Boolean {
        if(username.isBlank()) {
            Toast.makeText(requireContext(), "Username is required", Toast.LENGTH_SHORT).show()
            return false
        }
        if(email.isBlank()) {
            Toast.makeText(requireContext(), "Email is required", Toast.LENGTH_SHORT).show()
            return false
        }
        if(password.isBlank()) {
            Toast.makeText(requireContext(), "Password is required", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}