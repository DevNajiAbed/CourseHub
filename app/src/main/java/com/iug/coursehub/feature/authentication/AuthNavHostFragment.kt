package com.iug.coursehub.feature.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.iug.coursehub.R
import com.iug.coursehub.databinding.FragmentAuthNavHostBinding


class AuthNavHostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = viewModels<AuthViewModel>().value

        val binding = FragmentAuthNavHostBinding.inflate(inflater, container, false)

        viewModel.uiAction.observe(viewLifecycleOwner) { action ->
            when(action) {
                AuthViewModel.UiAction.NavigateToAdminScreen -> {
                    findNavController().navigate(R.id.action_authNavHostFragment_to_dashboardNavHostFragment)
                }
                AuthViewModel.UiAction.NavigateToUserScreen -> {
                    findNavController().navigate(R.id.action_authNavHostFragment_to_userNavHostFragment)
                }
            }
        }

        return binding.root
    }

    companion object {
        lateinit var viewModel: AuthViewModel
    }
}