package com.iug.coursehub.feature.user.my_courses_screen.lesson_list_screen;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.iug.coursehub.data.local.db.entity.Lesson;
import com.iug.coursehub.databinding.ItemLessonBinding;

import java.util.List;

public class LessonsAdapter extends RecyclerView.Adapter<LessonsAdapter.LessonViewHolder> {

    private final List<Lesson> lessons;
    private final OnShowContentClickListener onShowContentClick;

    public LessonsAdapter(List<Lesson> lessons, OnShowContentClickListener onShowContentClick) {
        this.lessons = lessons;
        this.onShowContentClick = onShowContentClick;
    }

    @Override
    public LessonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemLessonBinding binding = ItemLessonBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new LessonViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(LessonViewHolder holder, int position) {
        holder.binding.tvLessonTitle.setText(lessons.get(position).getTitle());
        holder.binding.btnShowContent.setOnClickListener(v -> onShowContentClick.onClick(lessons.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    public static class LessonViewHolder extends RecyclerView.ViewHolder {
        private final ItemLessonBinding binding;

        public LessonViewHolder(ItemLessonBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnShowContentClickListener {
        void onClick(int lessonId);
    }
}