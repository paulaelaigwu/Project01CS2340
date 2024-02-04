package com.example.project01cs2340;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddAssignmentDialogFragment extends DialogFragment {

    private EditText etCourseId, etAssignmentName, etDueDate;
    private Button btnSaveAssignment, btnCancelAssignment;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_add_assignment, null);

        etCourseId = view.findViewById(R.id.etCourseId);
        etAssignmentName = view.findViewById(R.id.etAssignmentName);
        etDueDate = view.findViewById(R.id.etDueDate);
        btnSaveAssignment = view.findViewById(R.id.btnSaveAssignment);
        btnCancelAssignment = view.findViewById(R.id.btnCancelAssignment);

        btnSaveAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveAssignmentClick();
            }
        });

        btnCancelAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelAssignmentClick();
            }
        });

        builder.setView(view)
                .setTitle("Add Assignment");

        return builder.create();
    }

    public void onSaveAssignmentClick() {
        String courseId = etCourseId.getText().toString().trim();
        String assignmentName = etAssignmentName.getText().toString().trim();
        String dueDate = etDueDate.getText().toString().trim();

        if (!courseId.isEmpty() && !assignmentName.isEmpty() && !dueDate.isEmpty()) {
            AssignmentModel assignment = new AssignmentModel(courseId, assignmentName, dueDate);

            Bundle result = new Bundle();
            result.putParcelable("assignment", (Parcelable) assignment);

            getParentFragmentManager().setFragmentResult("addAssignmentResult", result);

            dismiss();
        } else {
            System.out.println("Help!!!");
        }
    }

    public void onCancelAssignmentClick() {
        dismiss();
    }

    public void showAddAssignmentDialog(View view) {
        AddAssignmentDialogFragment dialog = new AddAssignmentDialogFragment();
        dialog.show(getActivity().getSupportFragmentManager(), "AddAssignmentDialog");
    }
}

