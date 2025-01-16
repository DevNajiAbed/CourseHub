package com.iug.coursehub.feature.user.my_courses_screen.lesson_list_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iug.coursehub.data.local.db.entity.Lesson
import com.iug.coursehub.databinding.ItemLessonBinding

class LessonsAdapter(
    private val lessons: List<Lesson>,
    private val onShowContentClick: (Int) -> Unit
) : RecyclerView.Adapter<LessonsAdapter.LessonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder(
            ItemLessonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.binding.apply {
            val lesson = lessons[position]

            tvLessonTitle.text = lesson.title
            btnShowContent.setOnClickListener {
                onShowContentClick(lesson.id!!)
            }
        }
    }

    override fun getItemCount(): Int {
        return lessons.size
    }

    class LessonViewHolder(val binding: ItemLessonBinding)
        : RecyclerView.ViewHolder(binding.root)
}