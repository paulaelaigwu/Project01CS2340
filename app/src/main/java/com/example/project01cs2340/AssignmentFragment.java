package com.example.project01cs2340;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AssignmentFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter assignmentAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG = "Assignments App";

    List<Assignment> assignmentList;
    EditText textviewCourseID, textviewAssignmentName, textviewDueDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assignmentList = AssignmentApplication.getAssignmentList();

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_slideshow, container, false);
    }

    public static AssignmentFragment newInstance(String param1, String param2) {
        AssignmentFragment fragment = new AssignmentFragment();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btnAddAssignment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog assignmentDialog = new Dialog(requireActivity());
                // Use the correct layout for the add assignment dialog
                assignmentDialog.setContentView(R.layout.fragment_add_assignment);
                assignmentDialog.setTitle("Add New Assignment");

                textviewCourseID = assignmentDialog.findViewById(R.id.etCourseId);
                textviewAssignmentName = assignmentDialog.findViewById(R.id.etAssignmentName);
                textviewDueDate = assignmentDialog.findViewById(R.id.etDueDate);

                Button addButton = assignmentDialog.findViewById(R.id.btnSaveAssignment);
                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Assignment newAssignment = new Assignment(textviewCourseID.getText().toString(), textviewAssignmentName.getText().toString(),
                                textviewDueDate.getText().toString());

                        assignmentList.add(newAssignment);
                        assignmentDialog.dismiss();
                        Toast.makeText(getActivity(), "New Assignment Added", Toast.LENGTH_LONG).show();

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

        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerViewAssignments);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        assignmentAdapter = new AssignmentAdapter(getContext(), assignmentList);
        recyclerView.setAdapter(assignmentAdapter);
    }
}

