package com.iug.coursehub.feature.user.my_courses_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iug.coursehub.R
import com.iug.coursehub.databinding.FragmentMyCoursesNavHostBinding

class MyCoursesNavHostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMyCoursesNavHostBinding.inflate(inflater, container, false)



        return binding.root
    }
}