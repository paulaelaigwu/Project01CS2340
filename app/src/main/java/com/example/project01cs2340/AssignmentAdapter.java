package com.example.project01cs2340;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder> {
    private Context context;
    private List<Assignment> assignmentList;

    public AssignmentAdapter(Context context, List<Assignment> assignmentList) {
        this.context = context;
        this.assignmentList = assignmentList;
    }

    @NonNull
    @Override
    public AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_item, parent, false);
        return new AssignmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_CourseID.setText(assignmentList.get(position).getID());
        holder.tv_AssignmentName.setText(assignmentList.get(position).getAssignment());
        holder.tv_DueDate.setText(assignmentList.get(position).getDueDate());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog assignmentDialog = new Dialog(context);
                assignmentDialog.setContentView(R.layout.fragment_add_assignment);
                assignmentDialog.setTitle("Edit Assignment");

                // Retrieve views from the dialog
                EditText etCourseId = assignmentDialog.findViewById(R.id.etCourseId);
                EditText etAssignmentName = assignmentDialog.findViewById(R.id.etAssignmentName);
                EditText etDueDate = assignmentDialog.findViewById(R.id.etDueDate);

                // Set initial values in the dialog
                etCourseId.setText(assignmentList.get(position).getID());
                etAssignmentName.setText(assignmentList.get(position).getAssignment());
                etDueDate.setText(assignmentList.get(position).getDueDate());

                Button ok = assignmentDialog.findViewById(R.id.btnSaveAssignment);
                ok.setText("OK");

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Update assignment data
                        Assignment updatedAssignment = new Assignment(
                                etCourseId.getText().toString(),
                                etAssignmentName.getText().toString(),
                                etDueDate.getText().toString()
                        );
                        assignmentList.set(position, updatedAssignment);
                        notifyItemChanged(position);
                        assignmentDialog.dismiss();
                        Toast.makeText(context, "Assignment Updated", Toast.LENGTH_LONG).show();
                    }
                });

                Button cancelButton = assignmentDialog.findViewById(R.id.btnCancelAssignment);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        assignmentDialog.cancel();
                    }
                });

                assignmentDialog.show();
            }
        });

        // Set long click listener for deleting
        holder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Delete Assignment?");
                builder.setCancelable(true);
                builder.setCancelable(false);

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        assignmentList.remove(position);
                        notifyItemRemoved(position);
                        dialog.dismiss();
                        Toast.makeText(context, "Assignment Deleted", Toast.LENGTH_LONG).show();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return assignmentList.size();
    }

    public static class AssignmentViewHolder extends RecyclerView.ViewHolder {
        TextView tv_CourseID;
        TextView tv_AssignmentName;
        TextView tv_DueDate;
        LinearLayout parentLayout;

        public AssignmentViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_CourseID = itemView.findViewById(R.id.textViewCourseId);
            tv_AssignmentName = itemView.findViewById(R.id.textViewAssignmentName);
            tv_DueDate = itemView.findViewById(R.id.textViewDueDate);
            parentLayout = itemView.findViewById(R.id.Card_AssignmentItem);

        }
    }
}
