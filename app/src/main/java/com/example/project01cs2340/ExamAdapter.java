package com.example.project01cs2340;

import android.app.AlertDialog;
import android.app.Dialog;
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

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamViewHolder> {

    private List<ExamModel> examList;

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
        holder.textViewCourseCode.setText(examList.get(position).getCourseCode());
        holder.textViewExamName.setText(examList.get(position).getExamName());
        holder.textViewDate.setText(examList.get(position).getDate());
        holder.textViewTime.setText(examList.get(position).getTime());
        holder.textViewLocation.setText(examList.get(position).getLocation());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog examDialog = new Dialog(view.getContext());
                examDialog.setContentView(R.layout.fragment_add_exam);
                examDialog.setTitle("Edit Exam");
                Button save = examDialog.findViewById(R.id.btnSaveExam);
                save.setText(R.string.ok);

                EditText etCourseCode = examDialog.findViewById(R.id.etCourseCode);
                EditText etExamName = examDialog.findViewById(R.id.etExamName);
                EditText etDate = examDialog.findViewById(R.id.etDate);
                EditText etTime = examDialog.findViewById(R.id.etTime);
                EditText etLocation = examDialog.findViewById(R.id.etLocation);


                etCourseCode.setText(examList.get(position).getCourseCode());
                etExamName.setText(examList.get(position).getExamName());
                etDate.setText(examList.get(position).getDate());
                etTime.setText(examList.get(position).getTime());
                etLocation.setText(examList.get(position).getLocation());

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ExamModel updatedExam = new ExamModel(etCourseCode.getText().toString(), etExamName.getText().toString(),
                                etDate.getText().toString(), etTime.getText().toString(), etLocation.getText().toString());

                        examList.set(position, updatedExam);
                        notifyItemChanged(position);
                        examDialog.dismiss();
                        Toast.makeText(view.getContext(), "Exam Updated", Toast.LENGTH_LONG).show();
                    }
                });

                Button cancelButton = examDialog.findViewById(R.id.btnCancelExam);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        examDialog.cancel();
                    }
                });

                examDialog.show();
            }
        });

        holder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Delete Course?");
                builder.setCancelable(true);
                builder.setCancelable(false);

                builder.setPositiveButton("Delete", (DialogInterface.OnClickListener) (dialog, which) -> {
                    examList.remove(position);
                    notifyItemRemoved(position);
                    dialog.dismiss();
                    Toast.makeText(view.getContext(), "Exam Deleted", Toast.LENGTH_LONG).show();
                });

                builder.setNegativeButton("Cancel", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return examList.size();
    }

    public static class ExamViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewCourseCode;
        private final TextView textViewExamName;
        private final TextView textViewDate;
        private final TextView textViewTime;
        private final TextView textViewLocation;
        LinearLayout parentLayout;

        public ExamViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCourseCode = itemView.findViewById(R.id.textViewCourseCode);
            textViewExamName = itemView.findViewById(R.id.textViewExamName);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewTime = itemView.findViewById(R.id.textViewTime);
            textViewLocation = itemView.findViewById(R.id.textViewLocation);
            parentLayout = itemView.findViewById(R.id.Card_ExamItem);
        }
    }
}

