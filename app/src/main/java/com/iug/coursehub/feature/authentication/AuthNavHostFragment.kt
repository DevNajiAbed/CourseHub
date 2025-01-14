package com.iug.coursehub.feature.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.iug.coursehub.R
import com.iug.coursehub.databinding.FragmentAuthNavHostBinding


class AuthNavHostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAuthNavHostBinding.inflate(inflater, container, false)
        return binding.root
    }
}