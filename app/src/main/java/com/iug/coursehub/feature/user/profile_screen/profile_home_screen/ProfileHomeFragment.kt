package com.iug.coursehub.feature.user.profile_screen.profile_home_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.iug.coursehub.R
import com.iug.coursehub.databinding.FragmentProfileHomeBinding
import com.iug.coursehub.databinding.FragmentProfileNavHostBinding
import com.iug.coursehub.feature.user.UserNavHostFragment

class ProfileHomeFragment : Fragment() {

    private val viewModel by viewModels<ProfileHomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.getUser()

        val binding = FragmentProfileHomeBinding.inflate(inflater, container, false)

        binding.apply {
            viewModel.user.observe(viewLifecycleOwner) { user ->
                if(user == null)
                    return@observe

                tvName.text = user.username
                tvEmail.text = user.email
                tvPassword.text = user.password
            }

            btnEditAccount.setOnClickListener {
                findNavController().navigate(R.id.action_profileHomeFragment_to_editUserFragment)
            }

            btnShowBookmarkedCourses.setOnClickListener {
                findNavController().navigate(R.id.action_profileHomeFragment_to_bookmarkedCoursesFragment)
            }

            btnSignOut.setOnClickListener {
                viewModel.signOut()
            }
        }

        viewModel.uiAction.observe(viewLifecycleOwner) { action ->
            when(action) {
                ProfileHomeViewModel.UiAction.NavigateToAuth -> {
                    UserNavHostFragment.viewModel.navigateFromUserToAuth()
                }
            }
        }

        return binding.root
    }
}