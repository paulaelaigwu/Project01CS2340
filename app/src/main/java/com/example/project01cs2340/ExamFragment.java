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

import java.util.ArrayList;
import java.util.List;

public class ExamFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter examAdapter;
    private RecyclerView.LayoutManager layoutManager;
    List<ExamModel> examList;
    EditText etCourseCode, etExamName, etDate, etTime, etLocation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        examList = ExamApplication.getExamList();
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

        view.findViewById(R.id.btnAddExam).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog examDialog = new Dialog(requireActivity());
                examDialog.setContentView(R.layout.fragment_add_exam);
                examDialog.setTitle("Add New Exam");

                etCourseCode = examDialog.findViewById(R.id.etCourseCode);
                etExamName = examDialog.findViewById(R.id.etExamName);
                etDate = examDialog.findViewById(R.id.etDate);
                etTime = examDialog.findViewById(R.id.etTime);
                etLocation = examDialog.findViewById(R.id.etLocation);

                Button addButton = examDialog.findViewById(R.id.btnSaveExam);
                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ExamModel newExam = new ExamModel(etCourseCode.getText().toString(), etExamName.getText().toString(),
                                etDate.getText().toString(), etTime.getText().toString(), etLocation.getText().toString());

                        examList.add(newExam);
                        examDialog.dismiss();
                        Toast.makeText(getActivity(), "New Exam Added", Toast.LENGTH_LONG).show();
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

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerViewExams);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        examAdapter = new ExamAdapter(examList);
        recyclerView.setAdapter(examAdapter);
    }
}