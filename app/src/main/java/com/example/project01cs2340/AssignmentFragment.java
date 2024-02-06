package com.example.project01cs2340;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AssignmentFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter assignmentAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG = "Assignments App";

    List<Assignment> assignmentList;
    EditText textviewCourseID, textviewAssignmentName, textviewDueDate;
    Menu menu;

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

        Spinner spinner = view.findViewById(R.id.spinnerSortAssignments);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
              @Override
              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                  if (position == 1) {
                      Collections.sort(assignmentList, Assignment.AssignmentDueDateComparator);
                      assignmentAdapter.notifyDataSetChanged();
                      Log.d(TAG, "onItemSelected: " + assignmentList.toString());
                      Toast.makeText(getActivity(), "Sorted by Due Date", Toast.LENGTH_LONG).show();
                  } else if (position == 2) {
                      Collections.sort(assignmentList, Assignment.AssignmentCourseIdComparator);
                      assignmentAdapter.notifyDataSetChanged();
                      Log.d(TAG, "onItemSelected: " + assignmentList.toString());

                      Toast.makeText(getActivity(), "Sorted by CourseId", Toast.LENGTH_LONG).show();
                  }
              }

              @Override
              public void onNothingSelected(AdapterView<?> parent) {
                  return;
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

