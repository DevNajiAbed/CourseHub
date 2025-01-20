package com.iug.coursehub.feature.dashboard.courses_screen;

import static androidx.navigation.Navigation.findNavController;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.iug.coursehub.R;
import com.iug.coursehub.data.local.db.CoursesRepository;
import com.iug.coursehub.data.local.db.entity.Course;
import com.iug.coursehub.databinding.FragmentCoursesBinding;
import com.iug.coursehub.feature.dashboard.lessons_screen.LessonsFragment;

import java.util.List;

public class CoursesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentCoursesBinding binding = FragmentCoursesBinding.inflate(inflater, container, false);

        CoursesViewModel viewModel = new ViewModelProvider(this).get(CoursesViewModel.class);

        binding.rvCourses.setLayoutManager(new LinearLayoutManager(requireContext()));
        LiveData<List<Course>> allCoursesLiveData = CoursesRepository.getAllCourses();
        allCoursesLiveData.observe(getViewLifecycleOwner(), new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> allCourses) {
                if (allCourses.isEmpty()) {
                    binding.content.setVisibility(View.GONE);
                    binding.sorryMessage.setVisibility(View.VISIBLE);
                } else {
                    binding.content.setVisibility(View.VISIBLE);
                    binding.sorryMessage.setVisibility(View.GONE);

                    CoursesAdapter adapter = new CoursesAdapter(
                            allCourses,
                            (id) -> {
                                Bundle bundle = new Bundle();
                                bundle.putInt(LessonsFragment.ARG_COURSE_ID, id);

                                findNavController(requireView()).navigate(
                                        R.id.action_coursesFragment_to_lessonsFragment,
                                        bundle
                                );
                            },
                            (id) -> {
                                Bundle bundle = new Bundle();
                                bundle.putInt(LessonsFragment.ARG_COURSE_ID, id);
                                findNavController(requireView()).navigate(
                                        R.id.action_coursesFragment_to_addEditCourseFragment,
                                        bundle
                                );
                            },
                            (course) -> {
                                new MaterialAlertDialogBuilder(requireContext())
                                        .setIcon(R.drawable.ic_delete)
                                        .setTitle("Deleting Course")
                                        .setMessage("Are you sure you want to delete this course?")
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                viewModel.deleteCourse(course);
                                                dialog.dismiss();
                                            }
                                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        }).create()
                                        .show();
                            }
                    );
                    binding.rvCourses.setAdapter(adapter);
                }
            }
        });

        return binding.getRoot();
    }
}
