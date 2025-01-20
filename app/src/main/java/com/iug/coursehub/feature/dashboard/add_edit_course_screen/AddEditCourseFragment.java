package com.iug.coursehub.feature.dashboard.add_edit_course_screen;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.iug.coursehub.R;
import com.iug.coursehub.data.local.db.CoursesRepository;
import com.iug.coursehub.data.local.db.entity.Course;
import com.iug.coursehub.databinding.FragmentAddEditCourseBinding;

import java.io.IOException;
import java.io.InputStream;

public class AddEditCourseFragment extends Fragment {

    public static final String ARG_COURSE_ID = "course_id";

    private FragmentAddEditCourseBinding binding;
    private ActivityResultLauncher<PickVisualMediaRequest> mediaPickerLauncher;
    private byte[] imageByteArray;
    private final AddEditCourseViewModel viewModel = new AddEditCourseViewModel();
    private Course course;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            int courseId = getArguments().getInt(ARG_COURSE_ID, 0);
            if (courseId > 0) {
                course = CoursesRepository.getCourseById(courseId);
                if (course != null) {
                    imageByteArray = course.getImageByteArray();
                    requireActivity().runOnUiThread(() -> fillFields(course));
                }
            }
        }

        mediaPickerLauncher = registerForActivityResult(
                new ActivityResultContracts.PickVisualMedia(),
                uri -> {
                    if (uri != null) {
                        imageByteArray = uriToByteArray(uri);
                        binding.courseImageView.setImageURI(uri);
                    } else {
                        Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddEditCourseBinding.inflate(inflater, container, false);

        binding.btnNavigateUp.setOnClickListener(v -> NavHostFragment.findNavController(this)
                .navigate(R.id.action_addEditCourseFragment_to_coursesFragment));

        binding.selectImageButton.setOnClickListener(v -> mediaPickerLauncher.launch(
                new PickVisualMediaRequest.Builder()
                        .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                        .build()
        ));

        final String[] selectedCategory = {""};
        binding.spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    selectedCategory[0] = "";
                } else {
                    selectedCategory[0] = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No action needed
            }
        });

        binding.saveButton.setOnClickListener(v -> {
            if (imageByteArray == null) return;

            String courseName = binding.nameEditText.getText().toString().trim();
            if (binding.priceEditText.getText().toString().isEmpty()) {
                Toast.makeText(requireContext(), "Price field shouldn't be empty", Toast.LENGTH_SHORT).show();
                return;
            }
            float price = Float.parseFloat(binding.priceEditText.getText().toString());

            if (binding.hoursEditText.getText().toString().isEmpty()) {
                Toast.makeText(requireContext(), "Number of hours' field shouldn't be empty", Toast.LENGTH_SHORT).show();
                return;
            }
            int noOfHours = Integer.parseInt(binding.hoursEditText.getText().toString());

            String lecturerName = binding.lecturerEditText.getText().toString().trim();

            Course course = new Course(
                    this.course != null ? this.course.getId() : null,
                    courseName,
                    imageByteArray,
                    lecturerName,
                    selectedCategory[0],
                    noOfHours,
                    price
            );

            viewModel.saveCourse(course);
        });

        viewModel.uiAction.observe(getViewLifecycleOwner(), action -> {
            if (action instanceof AddEditCourseViewModel.UiAction.ShowToast) {
                Toast.makeText(requireContext(),
                        ((AddEditCourseViewModel.UiAction.ShowToast) action).getMessage(),
                        Toast.LENGTH_SHORT).show();
            } else if (action instanceof AddEditCourseViewModel.UiAction.NavigateUp) {
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_addEditCourseFragment_to_coursesFragment);
            }
        });

        return binding.getRoot();
    }

    private void fillFields(Course course) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(course.getImageByteArray(), 0, course.getImageByteArray().length);
        binding.courseImageView.setImageBitmap(bitmap);
        binding.nameEditText.setText(course.getName());
        binding.priceEditText.setText(String.valueOf(course.getPrice()));
        binding.hoursEditText.setText(String.valueOf(course.getNoOfHours()));
        binding.lecturerEditText.setText(course.getLecturerName());
    }

    @SuppressLint("NewApi")
    private byte[] uriToByteArray(Uri uri) {
        try (InputStream inputStream = requireContext().getContentResolver().openInputStream(uri)) {
            if (inputStream != null) {
                return inputStream.readAllBytes();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
