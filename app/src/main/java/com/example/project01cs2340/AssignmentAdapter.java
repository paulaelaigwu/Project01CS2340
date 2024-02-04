package com.example.project01cs2340;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder> {

    private List<AssignmentModel> assignmentList;

    public AssignmentAdapter(List<AssignmentModel> assignmentList) {
        this.assignmentList = assignmentList;
    }

    @NonNull
    @Override
    public AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_item, parent, false);
        return new AssignmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentViewHolder holder, int position) {
        AssignmentModel assignment = assignmentList.get(position);
        holder.bind(assignment);
    }

    @Override
    public int getItemCount() {
        return assignmentList.size();
    }

    public static class AssignmentViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewCourseId, textViewAssignmentName, textViewDueDate;

        public AssignmentViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewCourseId = itemView.findViewById(R.id.textViewCourseId);
            textViewAssignmentName = itemView.findViewById(R.id.textViewAssignmentName);
            textViewDueDate = itemView.findViewById(R.id.textViewDueDate);
        }

        public void bind(AssignmentModel assignment) {
            textViewCourseId.setText("Course ID: " + assignment.getCourseId());
            textViewAssignmentName.setText("Assignment Name: " + assignment.getAssignmentName());
            textViewDueDate.setText("Due Date: " + assignment.getDueDate());
        }
    }
}

