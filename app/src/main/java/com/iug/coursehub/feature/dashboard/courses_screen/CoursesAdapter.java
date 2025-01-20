package com.iug.coursehub.feature.dashboard.courses_screen;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iug.coursehub.data.local.db.entity.Course;
import com.iug.coursehub.databinding.ItemCourseBinding;

import android.view.LayoutInflater;

import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CourseViewHolder> {

    private List<Course> courses;
    private OnShowLessonClick onShowLessonsClick;
    private OnEditCourseClick onEditCourseClick;
    private OnDeleteCourseClick onDeleteCourseClick;

    public CoursesAdapter(
            List<Course> courses,
            OnShowLessonClick onShowLessonsClick,
            OnEditCourseClick onEditCourseClick,
            OnDeleteCourseClick onDeleteCourseClick
    ) {
        this.courses = courses;
        this.onShowLessonsClick = onShowLessonsClick;
        this.onEditCourseClick = onEditCourseClick;
        this.onDeleteCourseClick = onDeleteCourseClick;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CourseViewHolder(
            ItemCourseBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
            )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courses.get(position);
        holder.getBinding().tvCourseName.setText(course.getName());
        holder.getBinding().tvPrice.setText(course.getPrice() + "");

        holder.getBinding().btnShowLessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShowLessonsClick.onShowLessonsClick(course.getId());
            }
        });

        holder.getBinding().btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditCourseClick.onEditCourseClick(course.getId());
            }
        });

        holder.getBinding().btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteCourseClick.onDeleteCourseClick(course);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    static class CourseViewHolder extends RecyclerView.ViewHolder {

        private ItemCourseBinding binding;

        public ItemCourseBinding getBinding() {
            return binding;
        }

        public CourseViewHolder(ItemCourseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
