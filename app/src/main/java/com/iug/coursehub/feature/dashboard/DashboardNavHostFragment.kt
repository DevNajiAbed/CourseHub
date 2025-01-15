package com.iug.coursehub.feature.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.iug.coursehub.R
import com.iug.coursehub.databinding.FragmentDashboardNavHostBinding
import com.iug.coursehub.feature.dashboard.courses_screen.CoursesFragment

class DashboardNavHostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = viewModels<DashboardNavHostViewModel>().value

        val binding = FragmentDashboardNavHostBinding.inflate(inflater, container, false)

        viewModel.uiAction.observe(viewLifecycleOwner) { action ->
            when(action) {
                DashboardNavHostViewModel.UiAction.NavigateToAuthNavHostScreen -> {
                    findNavController().navigate(R.id.action_dashboardNavHostFragment_to_authNavHostFragment)
                }
            }
        }

        return binding.root
    }

    companion object {
        lateinit var viewModel: DashboardNavHostViewModel
    }
}