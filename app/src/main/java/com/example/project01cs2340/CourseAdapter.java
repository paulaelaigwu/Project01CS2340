package com.example.project01cs2340;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.courseViewHolder> {
    public Context context;
    public List<Course> courseList;

    // Constructor
    public CourseAdapter(Context context, List<Course> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public courseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_layout, parent, false);
        return new courseViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull courseViewHolder holder, int position) {
        holder.tv_courseId.setText(courseList.get(position).getId());
        holder.tv_courseTitle.setText(courseList.get(position).getTitle());
        holder.tv_courseProfessor.setText(courseList.get(position).getProfessor());
        holder.tv_courseDays.setText(courseList.get(position).getDays());
        holder.tv_courseTime.setText(courseList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class courseViewHolder extends RecyclerView.ViewHolder {

        TextView tv_courseId;

        TextView tv_courseTitle;
        TextView tv_courseProfessor;
        TextView tv_courseDays;
        TextView tv_courseTime;
        public courseViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_courseId = itemView.findViewById(R.id.course_id);
            tv_courseTitle = itemView.findViewById(R.id.course_title);
            tv_courseProfessor = itemView.findViewById(R.id.course_professor);
            tv_courseDays = itemView.findViewById(R.id.course_days);
            tv_courseTime = itemView.findViewById(R.id.course_time);
        }
    }
}