package com.iug.coursehub.feature.user.profile_screen.bookmarked_courses_screen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.iug.coursehub.data.local.db.entity.Course;
import com.iug.coursehub.databinding.ItemCourseBinding;
import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CourseViewHolder> {

    private List<Course> courses;

    public CoursesAdapter(List<Course> courses) {
        this.courses = courses;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCourseBinding binding = ItemCourseBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new CourseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        holder.binding.btnEdit.setVisibility(View.GONE);
        holder.binding.btnDelete.setVisibility(View.GONE);
        holder.binding.btnShowLessons.setVisibility(View.GONE);

        Course course = courses.get(position);
        holder.binding.tvCourseName.setText(course.getName());
        holder.binding.tvPrice.setText("$" + course.getPrice());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        ItemCourseBinding binding;

        public CourseViewHolder(@NonNull ItemCourseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
