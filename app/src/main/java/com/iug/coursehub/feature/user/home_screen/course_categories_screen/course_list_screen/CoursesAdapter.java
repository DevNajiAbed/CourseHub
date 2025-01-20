package com.iug.coursehub.feature.user.home_screen.course_categories_screen.course_list_screen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.iug.coursehub.data.local.db.entity.Course;
import com.iug.coursehub.databinding.ItemCourseBinding;

import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CourseViewHolder> {

    private List<Course> courses;
    private final OnShowLessonsClick onShowLessonsClick;
    private final OnBookmarkClick onBookmarkClick;

    public CoursesAdapter(List<Course> courses, OnShowLessonsClick onShowLessonsClick, OnBookmarkClick onBookmarkClick) {
        this.courses = courses;
        this.onShowLessonsClick = onShowLessonsClick;
        this.onBookmarkClick = onBookmarkClick;
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCourseBinding binding = ItemCourseBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CourseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        holder.binding.btnEdit.setVisibility(View.GONE);
        holder.binding.btnDelete.setVisibility(View.GONE);
        holder.binding.btnBookmark.setVisibility(View.VISIBLE);
        holder.binding.btnShowLessons.setText("Show Details");

        Course course = courses.get(position);
        holder.binding.tvCourseName.setText(course.getName());
        holder.binding.tvPrice.setText("$" + course.getPrice());

        holder.binding.btnShowLessons.setOnClickListener(v -> onShowLessonsClick.onClick(course.getId()));
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        public final ItemCourseBinding binding;

        public CourseViewHolder(ItemCourseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnShowLessonsClick {
        void onClick(int courseId);
    }

    public interface OnBookmarkClick {
        void onClick(int courseId);
    }
}