package com.iug.coursehub.feature.dashboard.lessons_screen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.iug.coursehub.data.local.db.entity.Lesson;
import com.iug.coursehub.databinding.ItemLessonBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public class LessonsAdapter extends RecyclerView.Adapter<LessonsAdapter.LessonViewHolder> {
    @NotNull
    private final List<Lesson> lessons;
    @NotNull
    private final OnLessonClick onShowContentClick;

    public LessonsAdapter(@NotNull List<Lesson> lessons, @NotNull OnLessonClick onShowContentClick) {
        this.lessons = lessons;
        this.onShowContentClick = onShowContentClick;
    }

    @NotNull
    public LessonViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ItemLessonBinding var3 = ItemLessonBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new LessonViewHolder(var3);
    }

    public void onBindViewHolder(@NotNull LessonViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        ItemLessonBinding var3 = holder.getBinding();
        Lesson lesson = (Lesson)this.lessons.get(position);
        var3.btnShowContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShowContentClick.onLessonClick(lesson.getId());
            }
        });
    }

    public int getItemCount() {
        return this.lessons.size();
    }

    public static final class LessonViewHolder extends RecyclerView.ViewHolder {
        @NotNull
        private final ItemLessonBinding binding;

        public LessonViewHolder(@NotNull ItemLessonBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @NotNull
        public ItemLessonBinding getBinding() {
            return this.binding;
        }
    }
}
