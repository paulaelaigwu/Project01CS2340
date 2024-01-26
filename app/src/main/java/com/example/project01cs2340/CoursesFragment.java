package com.example.project01cs2340;


import android.animation.LayoutTransition;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CoursesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoursesFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter courseAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG = "Courses App";
    List<Course> coursesList;
    EditText id_input, title_input, professor_input, days_input, time_input;
    CheckBox monday, tuesday, wednesday, thursday, friday;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        coursesList = CourseApplication.getCoursesList();

        Log.d(TAG, "onCreate: " + coursesList.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_courses, container, false);
    }

    public static CoursesFragment newInstance(String param1, String param2) {
        CoursesFragment fragment = new CoursesFragment();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.add_new_course_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog courseDialog = new Dialog(requireActivity());
                courseDialog.setContentView(R.layout.course_dialog_layout);
                courseDialog.setTitle("Add New Course");


                id_input = courseDialog.findViewById(R.id.id_input);
                title_input = courseDialog.findViewById(R.id.title_input);
                professor_input = courseDialog.findViewById(R.id.professor_input);
                monday = (CheckBox) courseDialog.findViewById(R.id.monday);
                tuesday = (CheckBox) courseDialog.findViewById(R.id.tuesday);
                wednesday = (CheckBox) courseDialog.findViewById(R.id.wednesday);
                thursday = (CheckBox) courseDialog.findViewById(R.id.thursday);
                friday = (CheckBox) courseDialog.findViewById(R.id.friday);
                time_input = courseDialog.findViewById(R.id.time_input);

                Button addButton = courseDialog.findViewById(R.id.add_button);
                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String m = monday.isChecked() ? "M" : "";
                        String t = tuesday.isChecked() ? "T" : "";
                        String w = wednesday.isChecked() ? "W" : "";
                        String th = thursday.isChecked() ? "Th" : "";
                        String f = friday.isChecked() ? "F" : "";
                        String days = m + " " + t + " " + w + " " + th + " " + f;

                        Course newCourse = new Course(id_input.getText().toString(), title_input.getText().toString(),
                                    professor_input.getText().toString(), days, time_input.getText().toString());

                        coursesList.add(newCourse);
                        courseDialog.dismiss();
                        Toast.makeText(getActivity(), "New Course Added", Toast.LENGTH_LONG).show();
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

        recyclerView = (RecyclerView) getView().findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        courseAdapter = new CourseAdapter(getContext(), coursesList);
        recyclerView.setAdapter(courseAdapter);
    }
}