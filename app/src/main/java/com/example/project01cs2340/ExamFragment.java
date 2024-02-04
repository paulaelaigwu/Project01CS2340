package com.example.project01cs2340;

import android.os.Bundle;
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

public class ExamFragment extends Fragment {

    private final List<ExamModel> examList = new ArrayList<>();
    private ExamAdapter examAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        Button btnAddExam = rootView.findViewById(R.id.btnAddExam);
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewExams);

        examAdapter = new ExamAdapter(examList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(examAdapter);

        btnAddExam.setOnClickListener(v -> showAddExamDialog());

        return rootView;
    }

    private void showAddExamDialog() {
        ExamDialogFragment dialog = new ExamDialogFragment();
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.setFragmentResultListener("addExamResult", this, (requestKey, result) -> {
            ExamModel newExam = result.getParcelable("exam");
            examList.add(newExam);
            examAdapter.notifyDataSetChanged();
        });
        dialog.show(fragmentManager, "AddExamDialog");
    }
}
