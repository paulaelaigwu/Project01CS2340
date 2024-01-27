package com.example.project01cs2340;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
    public void onBindViewHolder(@NonNull courseViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_courseId.setText(courseList.get(position).getId());
        holder.tv_courseTitle.setText(courseList.get(position).getTitle());
        holder.tv_courseProfessor.setText(courseList.get(position).getProfessor());
        holder.tv_courseDays.setText(courseList.get(position).getDays());
        holder.tv_courseTime.setText(courseList.get(position).getTime());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog courseDialog = new Dialog(view.getContext());
                courseDialog.setContentView(R.layout.course_dialog_layout);
                courseDialog.setTitle("Edit Course");
                Button ok = courseDialog.findViewById(R.id.add_button);
                ok.setText("OK");

                EditText id_input = courseDialog.findViewById(R.id.id_input);
                EditText title_input = courseDialog.findViewById(R.id.title_input);
                EditText professor_input = courseDialog.findViewById(R.id.professor_input);
                CheckBox monday = (CheckBox) courseDialog.findViewById(R.id.monday);
                CheckBox tuesday = (CheckBox) courseDialog.findViewById(R.id.tuesday);
                CheckBox wednesday = (CheckBox) courseDialog.findViewById(R.id.wednesday);
                CheckBox thursday = (CheckBox) courseDialog.findViewById(R.id.thursday);
                CheckBox friday = (CheckBox) courseDialog.findViewById(R.id.friday);
                EditText time_input = courseDialog.findViewById(R.id.time_input);

                id_input.setText(courseList.get(position).getId());
                title_input.setText(courseList.get(position).getTitle());
                professor_input.setText(courseList.get(position).getProfessor());
                monday.setChecked(courseList.get(position).getDays().contains("M"));
                tuesday.setChecked(courseList.get(position).getDays().contains("T"));
                wednesday.setChecked(courseList.get(position).getDays().contains("W"));
                thursday.setChecked(courseList.get(position).getDays().contains("Th"));
                friday.setChecked(courseList.get(position).getDays().contains("F"));
                time_input.setText(courseList.get(position).getTime());

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String m = monday.isChecked() ? "M" : "";
                        String t = tuesday.isChecked() ? "T" : "";
                        String w = wednesday.isChecked() ? "W" : "";
                        String th = thursday.isChecked() ? "Th" : "";
                        String f = friday.isChecked() ? "F" : "";
                        String days = m + " " + t + " " + w + " " + th + " " + f;

                        Course updatedCourse = new Course(id_input.getText().toString(), title_input.getText().toString(),
                                professor_input.getText().toString(), days, time_input.getText().toString());

                        courseList.set(position, updatedCourse);
                        notifyItemChanged(position);
                        courseDialog.dismiss();
                        Toast.makeText(view.getContext(), "Course Updated", Toast.LENGTH_LONG).show();
                    }
                });

                Button cancelButton = courseDialog.findViewById(R.id.cancel_button);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        courseDialog.cancel();
                    }
                });

                courseDialog.show();
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
                    courseList.remove(position);
                    notifyItemRemoved(position);
                    dialog.dismiss();
                    Toast.makeText(view.getContext(), "Course Deleted", Toast.LENGTH_LONG).show();
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
        return courseList.size();
    }

    public class courseViewHolder extends RecyclerView.ViewHolder {

        TextView tv_courseId;

        TextView tv_courseTitle;
        TextView tv_courseProfessor;
        TextView tv_courseDays;
        TextView tv_courseTime;
        LinearLayout parentLayout;
        //LinearLayout parentLayout;
        public courseViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_courseId = itemView.findViewById(R.id.course_id);
            tv_courseTitle = itemView.findViewById(R.id.course_title);
            tv_courseProfessor = itemView.findViewById(R.id.course_professor);
            tv_courseDays = itemView.findViewById(R.id.course_days);
            tv_courseTime = itemView.findViewById(R.id.course_time);
            parentLayout = itemView.findViewById(R.id.linearLayout);

        }
    }
}