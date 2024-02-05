package com.example.project01cs2340;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExamFragment extends Fragment implements OnExamAddedListener {

    private final List<ExamModel> examList = new ArrayList<>();
    private ExamAdapter examAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    public static ExamFragment newInstance(String param1, String param2) {
        ExamFragment fragment = new ExamFragment();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRecyclerView(view);

        view.findViewById(R.id.btnAddExam).setOnClickListener(v -> showAddExamDialog());
    }

    private void setupRecyclerView(View rootView) {
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewExams);
        examAdapter = new ExamAdapter(examList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(examAdapter);
    }

    private void showAddExamDialog() {
        final Dialog examDialog = new Dialog(requireActivity());
        examDialog.setContentView(R.layout.fragment_add_exam);
        examDialog.setTitle("Add New Exam");

        Button addButton = examDialog.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExamModel newExam = createExamModelFromInputs(examDialog);

                examList.add(newExam);
                examAdapter.notifyDataSetChanged();

                examDialog.dismiss();
            }
        });

        Button cancelButton = examDialog.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                examDialog.cancel();
            }
        });

        examDialog.show();
    }



    private ExamModel createExamModelFromInputs(Dialog examDialog) {
        return null;
        // Not working when the ids are inserted so I just put null
    }

    @Override
    public void onExamAdded(ExamModel newExam) {

        examList.add(newExam);
        examAdapter.notifyDataSetChanged();
    }

    @Override
    public void onExamAdded() {

    }
}
