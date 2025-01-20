package com.iug.coursehub.feature.dashboard;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.iug.coursehub.R;
import com.iug.coursehub.databinding.FragmentDashboardNavHostBinding;

public class DashboardNavHostFragment extends Fragment {

    public static DashboardNavHostViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(DashboardNavHostViewModel.class);

        FragmentDashboardNavHostBinding binding = FragmentDashboardNavHostBinding.inflate(inflater, container, false);

        viewModel.getUiAction().observe(getViewLifecycleOwner(), new Observer<DashboardNavHostViewModel.UiAction>() {
            @Override
            public void onChanged(DashboardNavHostViewModel.UiAction action) {
                if (action == DashboardNavHostViewModel.UiAction.NAVIGATE_TO_AUTH_NAV_HOST_SCREEN) {
                    findNavController(requireView()).navigate(R.id.action_dashboardNavHostFragment_to_authNavHostFragment);
                }
            }
        });

        return binding.getRoot();
    }
}
