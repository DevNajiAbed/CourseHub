package com.iug.coursehub.feature.dashboard.courses_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iug.coursehub.data.local.db.entity.Course
import com.iug.coursehub.databinding.ItemCourseBinding

class CoursesAdapter(
    private var courses: List<Course>,
    private val onShowLessonsClick: (Int) -> Unit,
    private val onEditCourseClick: (Int) -> Unit,
    private val onDeleteCourseClick: (Course) -> Unit
) : RecyclerView.Adapter<CoursesAdapter.CourseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        return CourseViewHolder(
            ItemCourseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.binding.apply {
            val course = courses[position]
            tvCourseName.text = course.name
            tvPrice.text = "$${course.price}"

            btnShowLessons.setOnClickListener {
                onShowLessonsClick(course.id!!)
            }

            btnEdit.setOnClickListener {
                onEditCourseClick(course.id!!)
            }

            btnDelete.setOnClickListener {
                onDeleteCourseClick(course)
            }
        }
    }

    override fun getItemCount(): Int {
        return courses.size
    }


    class CourseViewHolder(val binding: ItemCourseBinding)
        : RecyclerView.ViewHolder(binding.root)
}