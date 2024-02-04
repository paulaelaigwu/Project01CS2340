package com.example.project01cs2340;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AssignmentFragment extends Fragment {

    private final List<AssignmentModel> assignmentList = new ArrayList<>();
    private AssignmentAdapter assignmentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_slideshow, container, false);

        Button btnAddAssignment = rootView.findViewById(R.id.btnAddAssignment);
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewAssignments);

        assignmentAdapter = new AssignmentAdapter(assignmentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(assignmentAdapter);

        btnAddAssignment.setOnClickListener(v -> showAddAssignmentDialog());

        return rootView;
    }

    private void showAddAssignmentDialog() {
        Log.d("AssignmentFragment", "showAddAssignmentDialog called");
        AddAssignmentDialogFragment dialog = new AddAssignmentDialogFragment();
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.setFragmentResultListener("addAssignmentResult", this, (requestKey, result) -> {
            AssignmentModel newAssignment = result.getParcelable("assignment");
            assignmentList.add(newAssignment);
            assignmentAdapter.notifyDataSetChanged();
        });
        dialog.show(fragmentManager, "AddAssignmentDialog");
    }

    }


