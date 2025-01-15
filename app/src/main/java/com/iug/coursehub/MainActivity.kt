package com.iug.coursehub

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.iug.coursehub.data.local.prefs.AppPrefs
import com.iug.coursehub.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainNavHost) as NavHostFragment
        val navController = navHostFragment.navController

        if(AppPrefs.getRememberMe()) {
            if(AppPrefs.isCurrUserAdmin()) {
                viewModel.navigateToDashboardNavHost()
            } else {
                viewModel.navigateToUserNavHost()
            }
        }

        viewModel.uiAction.observe(this) { action ->
            when(action) {
                MainViewModel.UiAction.NavigateToDashboardNavHost -> {
                    navController.navigate(R.id.action_authNavHostFragment_to_dashboardNavHostFragment)
                }

                MainViewModel.UiAction.NavigateToUserNavHost -> {

                }
                
                MainViewModel.UiAction.NavigateUserToAuthNavHost -> {

                }

                MainViewModel.UiAction.NavigateAdminToAuthNavHost -> {
                    navController.navigate(R.id.action_dashboardNavHostFragment_to_authNavHostFragment)
                    navController.popBackStack()
                }
            }
        }
    }
}