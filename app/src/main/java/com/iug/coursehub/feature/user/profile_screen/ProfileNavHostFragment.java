package com.iug.coursehub.feature.user.profile_screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.iug.coursehub.databinding.FragmentProfileNavHostBinding;

public class ProfileNavHostFragment extends Fragment {

    private FragmentProfileNavHostBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileNavHostBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
