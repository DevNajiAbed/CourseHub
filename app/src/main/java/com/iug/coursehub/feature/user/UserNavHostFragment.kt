package com.iug.coursehub.feature.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.iug.coursehub.MainActivity
import com.iug.coursehub.R
import com.iug.coursehub.databinding.FragmentUserNavHostBinding

class UserNavHostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = viewModels<UserNavHostViewModel>().value

        val binding = FragmentUserNavHostBinding.inflate(inflater, container, false)

        val navHostFragment = childFragmentManager.findFragmentById(R.id.user_nav_host) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)

        viewModel.uiAction.observe(viewLifecycleOwner) { action ->
            when(action) {
                UserNavHostViewModel.UiAction.NavigateFromUserToAuth -> {
                    (requireActivity() as MainActivity).viewModel.navigateUserToAuth()
                }
            }
        }

        return binding.root
    }

    companion object {
        lateinit var viewModel: UserNavHostViewModel
    }
}