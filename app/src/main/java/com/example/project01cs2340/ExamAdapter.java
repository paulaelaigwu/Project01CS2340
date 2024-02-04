package com.example.project01cs2340;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamViewHolder> {

    private final List<ExamModel> examList;

    public ExamAdapter(List<ExamModel> examList) {
        this.examList = examList;
    }

    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_item, parent, false);
        return new ExamViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamViewHolder holder, int position) {
        ExamModel exam = examList.get(position);
        holder.bind(exam);
    }

    @Override
    public int getItemCount() {
        return examList.size();
    }

    public static class ExamViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewCourseCode;
        private final TextView textViewExamName;
        private final TextView textViewDateTime;
        private final TextView textViewLocation;

        public ExamViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCourseCode = itemView.findViewById(R.id.textViewCourseCode);
            textViewExamName = itemView.findViewById(R.id.textViewExamName);
            textViewDateTime = itemView.findViewById(R.id.textViewDateTime);
            textViewLocation = itemView.findViewById(R.id.textViewLocation);
        }

        public void bind(ExamModel exam) {
            textViewCourseCode.setText(String.format("Course Code: %s", exam.getCourseCode()));
            textViewExamName.setText(String.format("Exam Name: %s", exam.getExamName()));
            textViewDateTime.setText(String.format("Date & Time: %s %s", exam.getDate(), exam.getTime()));
            textViewLocation.setText(String.format("Location: %s", exam.getLocation()));
        }
    }
}

