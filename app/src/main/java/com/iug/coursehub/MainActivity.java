package com.iug.coursehub;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.iug.coursehub.data.local.prefs.AppPrefs;
import com.iug.coursehub.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public MainViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), new OnApplyWindowInsetsListener() {
            @NonNull
            @Override
            public WindowInsetsCompat onApplyWindowInsets(@NonNull View v, @NonNull WindowInsetsCompat insets) {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            }
        });

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.mainNavHost);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();

        if (AppPrefs.getRememberMe()) {
            if (AppPrefs.isCurrUserAdmin()) {
                viewModel.navigateToDashboardNavHost();
            } else {
                viewModel.navigateToUserNavHost();
            }
        }

        viewModel.getUiAction().observe(this, new Observer<MainViewModel.UiAction>() {
            @Override
            public void onChanged(MainViewModel.UiAction action) {
                switch (action) {
                    case NAVIGATE_TO_DASHBOARD_NAV_HOST:
                        navController.navigate(R.id.action_authNavHostFragment_to_dashboardNavHostFragment);
                        break;
                    case NAVIGATE_TO_USER_NAV_HOST:
                        navController.navigate(R.id.action_authNavHostFragment_to_userNavHostFragment);
                        break;
                    case NAVIGATE_USER_TO_AUTH:
                        navController.navigate(R.id.action_homeNavHostFragment_to_authNavHostFragment);
                }
            }
        });
    }
}
