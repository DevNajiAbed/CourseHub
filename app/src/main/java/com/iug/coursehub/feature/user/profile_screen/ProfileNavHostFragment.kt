package com.iug.coursehub.feature.user.profile_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iug.coursehub.databinding.FragmentProfileNavHostBinding

class ProfileNavHostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentProfileNavHostBinding.inflate(inflater, container, false)
        return binding.root
    }
}