package com.iug.coursehub.feature.user.my_courses_screen.my_course_list_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iug.coursehub.data.local.db.entity.Course
import com.iug.coursehub.databinding.ItemCourseBinding

class CoursesAdapter(
    private var courses: List<Course>,
    private val onShowLessonsClick: (Int) -> Unit
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
            btnEdit.visibility = View.GONE
            btnDelete.visibility = View.GONE

            val course = courses[position]
            tvCourseName.text = course.name
            tvPrice.text = "$${course.price}"

            btnShowLessons.setOnClickListener {
                onShowLessonsClick(course.id!!)
            }
        }
    }

    override fun getItemCount(): Int {
        return courses.size
    }


    class CourseViewHolder(val binding: ItemCourseBinding)
        : RecyclerView.ViewHolder(binding.root)
}