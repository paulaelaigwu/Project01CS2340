package com.example.project01cs2340;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ExamDialogFragment extends DialogFragment {

    private EditText etCourseCode, etExamName, etDate, etTime, etLocation;
    private OnExamAddedListener onExamAddedListener;

    public void setOnExamAddedListener(OnExamAddedListener listener) {
        this.onExamAddedListener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_add_exam, null);

        etCourseCode = view.findViewById(R.id.etCourseCode);
        etExamName = view.findViewById(R.id.etExamName);
        etDate = view.findViewById(R.id.etDate);
        etTime = view.findViewById(R.id.etTime);
        etLocation = view.findViewById(R.id.etLocation);
        Button btnSaveExam = view.findViewById(R.id.btnSaveExam);
        Button btnCancelExam = view.findViewById(R.id.btnCancelExam);

        btnSaveExam.setOnClickListener(v -> onSaveExamClick());
        btnCancelExam.setOnClickListener(v -> onCancelExamClick());

        builder.setView(view)
                .setTitle("Add Exam");

        return builder.create();
    }

    private void onSaveExamClick() {
        String courseCode = etCourseCode.getText().toString().trim();
        String examName = etExamName.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        String time = etTime.getText().toString().trim();
        String location = etLocation.getText().toString().trim();

        if (!courseCode.isEmpty() && !examName.isEmpty() && !date.isEmpty() && !time.isEmpty() && !location.isEmpty()) {
            ExamModel exam = new ExamModel(courseCode, examName, date, time, location);
            onExamAddedListener.onExamAdded(exam);
            dismiss();
        } else {
            Toast.makeText(requireActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }

    private void onCancelExamClick() {
        dismiss();
    }
}
