package com.example.project01cs2340;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;


public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.ViewHolder> {

    private List<Assignment> assignmentList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onEditClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCourseID, tvCourseName, tvDueDate;
        private ImageButton btnEdit;

        public ViewHolder(View v) {
            super(v);
            tvCourseID = v.findViewById(R.id.tvCourseID);
            tvCourseName = v.findViewById(R.id.tvCourseName);
            tvDueDate = v.findViewById(R.id.tvDueDate);
            btnEdit = v.findViewById(R.id.floatingActionButton3);
        }
    }

    public AssignmentAdapter(List<Assignment> assignmentList) {
        this.assignmentList = assignmentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Assignment assignment = assignmentList.get(position);
        holder.tvCourseID.setText(assignment.getCourseID());
        holder.tvCourseName.setText(assignment.getCourseName());
        holder.tvDueDate.setText(assignment.getDateDue());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onEditClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return assignmentList.size();
    }

    // Sorting logic
    public void sortList(int position) {
        switch (position) {
            case 0: // Sort by Due Date
                Collections.sort(assignmentList, new DueDate());
                break;
            case 1: // Sort by Class
                Collections.sort(assignmentList, new ClassName());
                break;
        }
        notifyDataSetChanged();
    }
}
