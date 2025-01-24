package com.iug.coursehub.feature.user.home_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iug.coursehub.R
import com.iug.coursehub.databinding.FragmentUserHomeNavHostBinding
import com.iug.coursehub.feature.user.home_screen.cuorse_categories_screen.CategoriesPagerAdapter

class UserHomeNavHostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentUserHomeNavHostBinding.inflate(inflater, container, false)

        binding.apply {

        }

        return binding.root
    }
}