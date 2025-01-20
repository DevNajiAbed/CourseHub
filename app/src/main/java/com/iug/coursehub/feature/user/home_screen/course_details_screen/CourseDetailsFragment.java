package com.iug.coursehub.feature.user.home_screen.course_details_screen;

import static androidx.navigation.Navigation.findNavController;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.iug.coursehub.R;
import com.iug.coursehub.data.local.db.entity.Course;
import com.iug.coursehub.databinding.FragmentCourseDetailsBinding;

public class CourseDetailsFragment extends Fragment {

    private CourseDetailsViewModel viewModel;
    private Integer courseId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            courseId = getArguments().getInt(ARG_COURSE_ID, 0);
        }
        viewModel = new ViewModelProvider(this).get(CourseDetailsViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentCourseDetailsBinding binding = FragmentCourseDetailsBinding.inflate(inflater, container, false);

        viewModel.getCourseDetails(courseId);

        viewModel.getUiAction().observe(getViewLifecycleOwner(), action -> {
            if (action instanceof CourseDetailsViewModel.UiAction.CollectCourseDetails) {
                CourseDetails courseDetails = ((CourseDetailsViewModel.UiAction.CollectCourseDetails) action).getCourseDetails();
                fillFields(binding, courseDetails);
            } else if (action instanceof CourseDetailsViewModel.UiAction.ShowToast) {
                Toast.makeText(requireContext(), ((CourseDetailsViewModel.UiAction.ShowToast) action).getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnNavigateUp.setOnClickListener(v -> findNavController(requireView()).navigate(R.id.action_courseDetailsFragment_to_courseCategoriesFragment));
        binding.btnJoinCourse.setOnClickListener(v -> viewModel.joinCourse(courseId));

        return binding.getRoot();
    }

    private void fillFields(FragmentCourseDetailsBinding binding, CourseDetails courseDetails) {
        Course course = courseDetails.getCourse();
        byte[] imageByteArray = course.getImageByteArray();
        if (imageByteArray != null) {
            binding.courseImage.setImageBitmap(BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length));
        }
        binding.courseName.setText(course.getName());
        binding.coursePrice.setText("Price: $" + course.getPrice());
        binding.courseStudents.setText("Students Enrolled: " + courseDetails.getNoOfEnrolledStudents());
        binding.courseHours.setText("Total Hours: " + course.getNoOfHours());
        binding.courseContents.setText("The course contains " + courseDetails.getNoOfLessons() + " lessons.\nJoin the course to see them.");
        binding.courseLecturer.setText("Lecturer: [" + course.getLecturerName() + "]");
    }

    public static final String ARG_COURSE_ID = "course_id";
}